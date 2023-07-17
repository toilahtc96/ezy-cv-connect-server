package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.StepCode;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.Step;
import com.ezyfox.cvconnect.exception.ResourceNotFoundException;
import com.ezyfox.cvconnect.model.AddStepData;
import com.ezyfox.cvconnect.model.EditStepData;
import com.ezyfox.cvconnect.model.SearchStepData;
import com.ezyfox.cvconnect.repository.StepRepository;
import com.ezyfox.cvconnect.response.StepResponse;
import com.ezyfox.cvconnect.service.StepService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import lombok.AllArgsConstructor;
import org.eclipse.jetty.util.StringUtil;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class StepServiceImpl implements StepService {

    private final StepRepository stepRepository;
    private final DataToEntityConverter dataToEntityConverter;
    private final EntityToResponseConverter entityToResponseConverter;

    @Override
    public ResponseEntity addStep(AddStepData addStepData) {
        stepRepository.save(dataToEntityConverter.dataToStep(addStepData));
        return ResponseEntity.noContent();
    }

    @Override
    public ResponseEntity editStep(EditStepData editStepData) {
        Step stepById = stepRepository.findById(editStepData.getId());
        if (stepById == null) {
            throw new ResourceNotFoundException("Step");
        }
        if (editStepData.getCode() != null) {
            stepById.setCode(editStepData.getCode());
        }
        if (!StringUtil.isBlank(editStepData.getMeaning())) {
            stepById.setMeaning(editStepData.getMeaning());
        }
        if (editStepData.getStatus() != null) {
            stepById.setStatus(editStepData.getStatus());
        }
        if (editStepData.getOrder() != null) {
            stepById.setOrder(editStepData.getOrder());
        }
        stepRepository.save(stepById);
        return ResponseEntity.noContent();
    }

    @Override
    public void updateStepNext(Step step) {
        switch (step.getCode()) {
            case SEND_CV_TO_AGENCY: {
                step.setCode(StepCode.SEND_CV_TO_COMPANY);
                break;
            }
            case SEND_CV_TO_COMPANY: {
                step.setCode(StepCode.SUCCESS);
                break;
            }
            default:
                break;
        }
        stepRepository.save(step);
    }

    @Override
    public void updateStepPrev(Step step) {
        switch (step.getCode()) {
            case SEND_CV_TO_COMPANY: {
                step.setCode(StepCode.SEND_CV_TO_AGENCY);
                break;
            }
            case FAIL:
            case SUCCESS: {
                step.setCode(StepCode.SEND_CV_TO_COMPANY);
                break;
            }
            default:
                break;
        }
        stepRepository.save(step);
    }

    @Override
    public void updateStepCode(EditStepData editStepData, StepCode stepCode) {
        Step stepById = stepRepository.findById(editStepData.getId());
        if (stepById == null) {
            throw new ResourceNotFoundException("Step");
        }
        if (!stepById.getStatus().equals(EntityStatus.ACTIVED)) {
            throw new ResourceNotFoundException("Step Active");
        }
        if (editStepData.getCode() != null) {
            stepById.setCode(editStepData.getCode());
        }
        stepRepository.save(stepById);
    }

    @Override
    public List<StepResponse> getByStepCode(StepCode stepCode) {
        return stepRepository.findByStepCode(stepCode).stream()
                .map(entityToResponseConverter::toStepResponse).collect(Collectors.toList());
    }

    @Override
    public List<StepResponse> getByStatus(EntityStatus status) {
        return stepRepository.findByStatus(status).stream()
                .map(entityToResponseConverter::toStepResponse).collect(Collectors.toList());
    }

    @Override
    public List<StepResponse> getAll() {
        return stepRepository.findAll().stream().map(entityToResponseConverter::toStepResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StepResponse getById(Long stepId) {
        return entityToResponseConverter.toStepResponse(stepRepository.findById(stepId));
    }

    @Override
    public Map<String, Object> getStepPaging(SearchStepData searchStepData) {
        Map<String, Object> data = new HashMap<>();
        List<StepResponse> listData = stepRepository.searchStep(
                        searchStepData.getStepCode(),
                        searchStepData.getMeaning(),
                        searchStepData.getStatus(),
                        searchStepData.getSize(),
                        searchStepData.getSkip())
                .stream()
                .map(entityToResponseConverter::toStepResponse)
                .collect(Collectors.toList());
        BigInteger total = stepRepository.totalSearchStep(
                searchStepData.getStepCode(),
                searchStepData.getMeaning(),
                searchStepData.getStatus());
        data.put("data", listData);
        data.put("total", total);
        return data;
    }
}
