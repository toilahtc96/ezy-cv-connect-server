package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.WorkingForm;
import com.ezyfox.cvconnect.exception.ResourceNotFoundException;
import com.ezyfox.cvconnect.model.AddWorkingFormData;
import com.ezyfox.cvconnect.model.SearchWorkingFormData;
import com.ezyfox.cvconnect.model.WorkingFormData;
import com.ezyfox.cvconnect.repository.WorkingFormRepository;
import com.ezyfox.cvconnect.response.CompanyResponse;
import com.ezyfox.cvconnect.response.WorkingFormResponse;
import com.ezyfox.cvconnect.service.WorkingFormService;
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
public class WorkingFormServiceImpl implements WorkingFormService {

    private final WorkingFormRepository workingFormRepository;
    private final DataToEntityConverter dataToEntityConverter;
    private final EntityToResponseConverter entityToResponseConverter;


    @Override
    public void addWorkingForm(AddWorkingFormData workingFormData) {
        WorkingForm workingForm = dataToEntityConverter.dataToWorkingForm(workingFormData);
        workingFormRepository.save(workingForm);
    }

    @Override
    public void editWorkingForm(WorkingFormData workingFormData) {
        WorkingForm workingFormById = workingFormRepository.findById(workingFormData.getId());
        if (workingFormById == null) {
            throw new ResourceNotFoundException("WorkingForm By Id");
        }
        if (workingFormData.getName() != null) {
            workingFormById.setName(workingFormData.getName());
        }
        workingFormById.setStatus(workingFormData.getStatus());
        workingFormById.setUpdatedTime(LocalDateTime.now());
        workingFormById.setDescription(workingFormData.getDescription());
        workingFormRepository.save(workingFormById);
    }

    @Override
    public List<WorkingFormResponse> getWorkingFormByCodeActive(String code) {
        return entityToResponseConverter.toListWorkingFormResponse(
                workingFormRepository.getWorkingFormByCodeAndStatus(code, EntityStatus.ACTIVED)
        );
    }

    @Override
    public List<WorkingFormResponse> getWorkingFormByNameActive(String name) {
        return entityToResponseConverter.toListWorkingFormResponse(
                workingFormRepository.getWorkingFormByNameAndStatus(name, EntityStatus.ACTIVED)
        );
    }

    @Override
    public List<WorkingFormResponse> getAllWorkingFormActive() {
        return entityToResponseConverter.toListWorkingFormResponse(
                workingFormRepository.getAllWorkingFormByStatus(EntityStatus.ACTIVED)
        );
    }

    @Override
    public List<WorkingFormResponse> getAllWorkingForm() {
        return entityToResponseConverter.toListWorkingFormResponse(workingFormRepository.getAllWorkingForm());
    }

    @Override
    public List<WorkingFormResponse> getPaging(int page, int size) {
        int skip = page * size;
        return workingFormRepository
                .findAll(skip, size)
                .stream()
                .map(entityToResponseConverter::toWorkingFormResponse)
                .collect(Collectors.toList());
    }

    @Override
    public WorkingFormResponse getById(long id) {
        return entityToResponseConverter.toWorkingFormResponse(workingFormRepository.findById(id));
    }

    @Override
    public Map<String, Object> getByField(SearchWorkingFormData searchWorkingFormData) {
        int skip = searchWorkingFormData.getPage() * searchWorkingFormData.getSize();
        Map<String, Object> mapData = new HashMap<>();
        List<WorkingFormResponse> listData = workingFormRepository.searchWorkingForm(
                        searchWorkingFormData.getName(),
                        searchWorkingFormData.getSize(),
                        skip
                )
                .stream()
                .map(entityToResponseConverter::toWorkingFormResponse)
                .collect(Collectors.toList());
        BigInteger totalElementByField = workingFormRepository.totalSearchWorkingForm(
                searchWorkingFormData.getName()
        );
        mapData.put("data", listData);
        mapData.put("total", totalElementByField);
        return mapData;
    }
}
