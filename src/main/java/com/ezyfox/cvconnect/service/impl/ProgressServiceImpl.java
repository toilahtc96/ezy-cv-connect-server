package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.StepCode;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.Progress;
import com.ezyfox.cvconnect.entity.Job;
import com.ezyfox.cvconnect.entity.Step;
import com.ezyfox.cvconnect.exception.NotFoundException;
import com.ezyfox.cvconnect.exception.ResourceNotFoundException;
import com.ezyfox.cvconnect.model.AddProgressData;
import com.ezyfox.cvconnect.model.EditProgressData;
import com.ezyfox.cvconnect.model.SearchProgressData;
import com.ezyfox.cvconnect.repository.ProgressRepository;
import com.ezyfox.cvconnect.repository.JobRepository;
import com.ezyfox.cvconnect.repository.StepRepository;
import com.ezyfox.cvconnect.response.ProgressResponse;
import com.ezyfox.cvconnect.service.ProgressService;
import com.ezyfox.cvconnect.util.DateUtil;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
@Slf4j
public class ProgressServiceImpl implements ProgressService {

  private final ProgressRepository progressRepository;
  private final JobRepository jobRepository;
  private final StepRepository stepRepository;
  private final EntityToResponseConverter entityToResponseConverter;
  private final DataToEntityConverter dataToEntityConverter;

  @Override
  public void addProgress(AddProgressData addProgressData) {
    progressRepository.save(dataToEntityConverter.dataToProgress(addProgressData));
  }

  @Override
  public void editProgress(EditProgressData editProgressData) {
    Progress progressById = progressRepository.findById(editProgressData.getId());
    if (progressById == null) {
      throw new ResourceNotFoundException("Progress By Id");
    }
    progressById.setAgencyId(editProgressData.getAgencyId());
    progressById.setCandidateId(editProgressData.getCandidateId());
    progressById.setStepId(editProgressData.getStepId());
    progressById.setStatus(editProgressData.getStatus());
    progressById.setUpdatedTime(LocalDateTime.now());
    progressRepository.save(progressById);
  }

  @Override
  public List<ProgressResponse> getByAgencyId(long agencyId) {
    return progressRepository
        .getByAgencyId(agencyId)
        .stream()
        .map(entityToResponseConverter::toProgressResponse)
        .collect(Collectors.toList());
  }

  @Override
  public List<ProgressResponse> getByCandidateId(long candidateId) {
    return progressRepository
        .getByCandidateId(candidateId)
        .stream()
        .map(entityToResponseConverter::toProgressResponse)
        .collect(Collectors.toList());
  }

  @Override
  public List<ProgressResponse> getAll() {
    return progressRepository
        .findAll()
        .stream()
        .map(entityToResponseConverter::toProgressResponse)
        .collect(Collectors.toList());
  }

  @Override
  public Map<String, Object> getPageActiveOfAgency(
      long agencyId,
      Long companyId,
      String fromCandidateTime,
      String toCandidateTime,
      String sendCompanyFrom,
      String sendCompanyTo,
      int page,
      int size
  ) throws ParseException {
    Map<String, Object> data = new HashMap<>();
    Date fromCandidateTimeToDate = fromCandidateTime == null ? null :
        DateUtil.parseFromStringFormat(fromCandidateTime, DateUtil.DATE_DDMMYYYY_PATTERN);
    Date toCandidateTimeToDate = toCandidateTime == null ? null :
        DateUtil.parseFromStringFormat(toCandidateTime, DateUtil.DATE_DDMMYYYY_PATTERN);
    Date sendCompanyFromToDate = sendCompanyFrom == null ? null :
        DateUtil.parseFromStringFormat(sendCompanyFrom, DateUtil.DATE_DDMMYYYY_PATTERN);
    Date sendCompanyToToDate = sendCompanyTo == null ? null :
        DateUtil.parseFromStringFormat(sendCompanyTo, DateUtil.DATE_DDMMYYYY_PATTERN);
    int skip = size * page;
    List<ProgressResponse> listProgress = progressRepository
        .getActiveProgressByAgencyId(
            agencyId,
            EntityStatus.ACTIVED.name(),
            companyId,
            fromCandidateTimeToDate,
            toCandidateTimeToDate,
            sendCompanyFromToDate,
            sendCompanyToToDate,
            size,
            skip
        )
        .stream()
        .map(entityToResponseConverter::toProgressResponse)
        .sorted(
            Comparator.comparing(
                ProgressResponse::getCandidateSendCvTime,
                Comparator.nullsLast(Comparator.naturalOrder())
            ).reversed()
        )
        .collect(Collectors.toList());
    BigInteger total = progressRepository.totalActiveProgressByAgencyId(agencyId, EntityStatus.ACTIVED.name());
    data.put("data", listProgress);
    data.put("total", total);
    return data;
  }

  @Override
  public Map<String, Object> getProgressPaging(SearchProgressData searchProgressData) {
    Map<String, Object> data = new HashMap<>();
    List<ProgressResponse> listData = progressRepository
        .searchProgress(
            searchProgressData.getAgencyId(),
            searchProgressData.getCandidateId(),
            searchProgressData.getStepId(),
            searchProgressData.getStatus(),
            searchProgressData.getSize(),
            searchProgressData.getSkip()
        )
        .stream()
        .map(entityToResponseConverter::toProgressResponse)
        .collect(Collectors.toList());
    BigInteger total = progressRepository.totalSearchProgress(
        searchProgressData.getAgencyId(),
        searchProgressData.getCandidateId(),
        searchProgressData.getStepId(),
        searchProgressData.getStatus());
    data.put("data", listData);
    data.put("total", total);
    return data;
  }

  @Override
  public ProgressResponse getById(long id) {
    return entityToResponseConverter.toProgressResponse(progressRepository.findById(id));
  }

  @Override
  public List<ProgressResponse> getByCandidateJob(long candidateId, long jobId) {
    List<ProgressResponse> progressOfCandidate = progressRepository
        .getAllByAndCandidateIdAndJobId(
            candidateId,
            jobId)
        .stream()
        .map(entityToResponseConverter::toProgressResponse)
        .collect(Collectors.toList());
    if (progressOfCandidate.size() == 0) {
      initProgressOnJobForCandidate(candidateId, jobId);
    }
    return progressRepository
        .getAllByAndCandidateIdAndJobId(
            candidateId,
            jobId)
        .stream()
        .map(entityToResponseConverter::toProgressResponse)
        .collect(Collectors.toList());
  }

  private void initProgressOnJobForCandidate(long candidateId, long jobId) {
    Job jobById = jobRepository.findById(jobId);
    if (jobById == null) {
      throw new NotFoundException("Job by id: " + jobById + " not found");
    }

    Progress progress = new Progress();
    progress.setCandidateId(candidateId);
    progress.setAgencyId(1L);
    progress.setJobId(jobId);
    progress.setStatus(EntityStatus.ACTIVED);
    List<Step> stepes = stepRepository.findByStepCode(StepCode.SEND_CV_TO_AGENCY);
    if (!stepes.isEmpty()) {
      Step initStep = stepes.get(0);
      progress.setStepId(initStep != null ? initStep.getId() : null);
    }
    progress.setCreatedTime(LocalDateTime.now());
    progressRepository.save(progress);
  }

  @Override
  public void updateCvLink(long candidateId, long agencyId, long jobId, String cvLink) {

    Job jobById = jobRepository.findById(jobId);
    if (jobById == null) {
      throw new NotFoundException("Job by id: " + jobById + " not found");
    }

    Progress progress = progressRepository
        .getActiveProgressByCandidateAndAgencyAndJob(
            candidateId,
            agencyId,
            jobId,
            EntityStatus.ACTIVED
        );
    nextStepForProgress(progress);
    progress.setCvLink(cvLink);
    progressRepository.save(progress);
  }

  private void nextStepForProgress(Progress progress) {

    Step currentStep = stepRepository.findById(progress.getStepId());
    if (currentStep == null) {
      return;
    }
    Step nextStep = null;
    switch (currentStep.getCode()) {
      default:
        break;
      case SEND_CV_TO_AGENCY:
        log.info("next step from {} to {}", StepCode.SEND_CV_TO_AGENCY.getName(), StepCode.SEND_CV_TO_COMPANY.name());
        nextStep = stepRepository.findFirstByStepCode(StepCode.SEND_CV_TO_COMPANY);
        if (nextStep != null) {
          progress.setStepId(nextStep.getId());
        }
        break;
      case SEND_CV_TO_COMPANY:
        log.info("next step from {} to {}", StepCode.SEND_CV_TO_COMPANY.getName(), StepCode.SUCCESS.name());
        nextStep = stepRepository.findFirstByStepCode(StepCode.SUCCESS);
        if (nextStep != null) {
          progress.setStepId(nextStep.getId());
        }
        break;
    }
  }
}
