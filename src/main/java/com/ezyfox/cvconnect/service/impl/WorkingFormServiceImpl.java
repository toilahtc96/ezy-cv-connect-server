package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.JobType;
import com.ezyfox.cvconnect.exception.ResourceNotFoundException;
import com.ezyfox.cvconnect.model.AddJobTypeData;
import com.ezyfox.cvconnect.model.JobTypeData;
import com.ezyfox.cvconnect.repository.JobTypeRepository;
import com.ezyfox.cvconnect.repository.JobTypeRepository;
import com.ezyfox.cvconnect.response.JobTypeResponse;
import com.ezyfox.cvconnect.service.JobTypeService;
import com.ezyfox.cvconnect.service.JobTypeService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class JobTypeServiceImpl implements JobTypeService {

    private final JobTypeRepository jobTypeRepository;
    private final DataToEntityConverter dataToEntityConverter;
    private final EntityToResponseConverter entityToResponseConverter;


    @Override
    public void addJobType(AddJobTypeData jobTypeData) {
        JobType jobType = dataToEntityConverter.dataToJobType(jobTypeData);
        jobTypeRepository.save(jobType);
    }

    @Override
    public void editJobType(JobTypeData jobTypeData) {
        JobType jobTypeById = jobTypeRepository.findById(jobTypeData.getId());
        if (jobTypeById == null) {
            throw new ResourceNotFoundException("JobType By Id");
        }
        if (jobTypeData.getName() != null) {
            jobTypeById.setName(jobTypeData.getName());
        }
        jobTypeById.setStatus(jobTypeData.getStatus());
        jobTypeById.setUpdatedTime(LocalDateTime.now());
        jobTypeById.setDescription(jobTypeData.getDescription());
        jobTypeRepository.save(jobTypeById);
    }

    @Override
    public List<JobTypeResponse> getJobTypeByCodeActive(String code) {
        return entityToResponseConverter.toListJobTypeResponse(
                jobTypeRepository.getJobTypeByCodeAndStatus(code, EntityStatus.ACTIVED)
        );
    }

    @Override
    public List<JobTypeResponse> getJobTypeByNameActive(String name) {
        return entityToResponseConverter.toListJobTypeResponse(
                jobTypeRepository.getJobTypeByNameAndStatus(name, EntityStatus.ACTIVED)
        );
    }

    @Override
    public List<JobTypeResponse> getAllJobTypeActive() {
        return entityToResponseConverter.toListJobTypeResponse(
                jobTypeRepository.getAllJobTypeByStatus(EntityStatus.ACTIVED)
        );
    }

    @Override
    public List<JobTypeResponse> getAllJobType() {
        return entityToResponseConverter.toListJobTypeResponse(jobTypeRepository.getAllJobType());
    }

    @Override
    public List<JobTypeResponse> getPaging(int page, int size) {
        int skip = page * size;
        return jobTypeRepository
                .findAll(skip, size)
                .stream()
                .map(entityToResponseConverter::toJobTypeResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JobTypeResponse getById(long id) {
        return entityToResponseConverter.toJobTypeResponse(jobTypeRepository.findById(id));
    }
}
