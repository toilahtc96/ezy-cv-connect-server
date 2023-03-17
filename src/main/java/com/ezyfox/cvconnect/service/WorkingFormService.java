package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.AddWorkingFormData;
import com.ezyfox.cvconnect.model.SearchWorkingFormData;
import com.ezyfox.cvconnect.model.WorkingFormData;
import com.ezyfox.cvconnect.response.WorkingFormResponse;

import java.util.List;
import java.util.Map;

public interface WorkingFormService {

    void addWorkingForm(AddWorkingFormData workingFormData);

    void editWorkingForm(WorkingFormData workingFormData);

    List<WorkingFormResponse> getWorkingFormByCodeActive(String code);

    List<WorkingFormResponse> getWorkingFormByNameActive(String name);

    List<WorkingFormResponse> getAllWorkingFormActive();

    List<WorkingFormResponse> getAllWorkingForm();

    List<WorkingFormResponse> getPaging(int page, int size);

    WorkingFormResponse getById(long id);

    Map<String, Object> getByField(SearchWorkingFormData searchWorkingFormData);

}
