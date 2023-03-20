package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.JobType;
import com.ezyfox.cvconnect.exception.ResourceNotFoundException;
import com.ezyfox.cvconnect.model.*;
import com.ezyfox.cvconnect.repository.JobRepository;
import com.ezyfox.cvconnect.repository.JobTypeRepository;
import com.ezyfox.cvconnect.response.CompanyResponse;
import com.ezyfox.cvconnect.response.JobResponse;
import com.ezyfox.cvconnect.response.JobTypeResponse;
import com.ezyfox.cvconnect.service.JobService;
import com.ezyfox.cvconnect.service.JobTypeService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final DataToEntityConverter dataToEntityConverter;
    private final EntityToResponseConverter entityToResponseConverter;


    @Override
    public void addJob(AddJobData addJobData, Long userId) {
        jobRepository.save(dataToEntityConverter.dataToJob(addJobData, userId));
    }

    @Override
    public Map<String, Object> getByField(SearchJobData searchJobData) {
        int skip = searchJobData.getPage() * searchJobData.getSize();
        Map<String, Object> mapData = new HashMap<>();
        List<JobResponse> listData = jobRepository.searchJob(
                searchJobData.getJobTypeId(),
                searchJobData.getCompanyId(),
                searchJobData.getRangeSalary(),
                searchJobData.getLevelId(),
                searchJobData.getCustomRange(),
                searchJobData.getCareerId(),
                searchJobData.getWorkingFormId(),
                searchJobData.getStatus(),
                searchJobData.getInformation(),
                searchJobData.getSize(),
                skip
            )
            .stream()
            .map(entityToResponseConverter::toJobResponse)
            .collect(Collectors.toList());
        BigInteger totalElementByField = jobRepository.totalSearchJob(
            searchJobData.getJobTypeId(),
            searchJobData.getCompanyId(),
            searchJobData.getRangeSalary(),
            searchJobData.getLevelId(),
            searchJobData.getCustomRange(),
            searchJobData.getCareerId(),
            searchJobData.getWorkingFormId(),
            searchJobData.getStatus(),
            searchJobData.getInformation()
        );
        mapData.put("data", listData);
        mapData.put("total", totalElementByField);
        return mapData;
    }

    @Override
    public JobResponse getById(long id) {
        return entityToResponseConverter.toJobResponse(jobRepository.findById(id));
    }

    @Override
    public void editJob(EditJobData editJobData) {
        jobRepository.save(dataToEntityConverter.editDataToJob(editJobData));
    }
}
