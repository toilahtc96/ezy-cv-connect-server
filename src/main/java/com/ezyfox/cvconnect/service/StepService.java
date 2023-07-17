package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.StepCode;
import com.ezyfox.cvconnect.entity.Step;
import com.ezyfox.cvconnect.model.AddStepData;
import com.ezyfox.cvconnect.model.EditStepData;
import com.ezyfox.cvconnect.model.SearchStepData;
import com.ezyfox.cvconnect.response.StepResponse;
import com.tvd12.ezyhttp.core.response.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface StepService {

    ResponseEntity addStep(AddStepData addStepData);

    ResponseEntity editStep(EditStepData editStepData);

    void updateStepNext(Step step);

    void updateStepPrev(Step step);

    void updateStepCode(EditStepData editStepData, StepCode stepCode);

    List<StepResponse> getByStepCode(StepCode stepCode);

    List<StepResponse> getByStatus(EntityStatus status);

    List<StepResponse> getAll();

    StepResponse getById(Long stepId);

    Map<String, Object> getStepPaging(SearchStepData searchStepData);
}
