package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.StepCode;
import com.ezyfox.cvconnect.converter.EntityToDataConverter;
import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.entity.Step;
import com.ezyfox.cvconnect.model.AddStepData;
import com.ezyfox.cvconnect.model.EditStepData;
import com.ezyfox.cvconnect.model.SearchStepData;
import com.ezyfox.cvconnect.repository.StepRepository;
import com.ezyfox.cvconnect.request.AddStepRequest;
import com.ezyfox.cvconnect.request.EditStepRequest;
import com.ezyfox.cvconnect.request.UpdateStepRequest;
import com.ezyfox.cvconnect.response.StepResponse;
import com.ezyfox.cvconnect.service.StepService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller("api/v1/step")
@AllArgsConstructor
@Authenticated
public class StepController {

    private final StepService stepService;
    private final StepRepository stepRepository;
    private final RequestToDataConverter requestToDataConverter;
    private final EntityToDataConverter entityToDataConverter;

    @DoPost("/add")
    public ResponseEntity addStep(@RequestBody AddStepRequest addStepRequest) {
        AddStepData addStepData = requestToDataConverter.toDataFromAddStep(addStepRequest);
        stepService.addStep(addStepData);
        return ResponseEntity.noContent();
    }

    @DoPost("/edit")
    public ResponseEntity editAddress(@RequestBody EditStepRequest editStepRequest) {
        EditStepData addStepData = requestToDataConverter.toDataFromEditStep(editStepRequest);
        stepService.editStep(addStepData);
        return ResponseEntity.noContent();
    }

    @DoGet("/code")
    public List<StepResponse> getByCode(@RequestParam(name = "code") StepCode stepCode) {
        return stepService.getByStepCode(stepCode);
    }

    @DoGet("/status")
    public List<StepResponse> getByStatus(@RequestParam EntityStatus status) {
        return stepService.getByStatus(status);
    }

    @DoGet("/get-all")
    public List<StepResponse> getAll() {
        return stepService.getAll().stream().filter(step -> !step.getCode().equals(StepCode.FAIL))
                .collect(Collectors.toList());
    }

    @DoGet("/get-by-id")
    public StepResponse getById(@RequestParam long id) {
        return stepService.getById(id);
    }

    @DoPut("/update-next-step")
    public void updateNextStep(@RequestBody UpdateStepRequest updateStepRequest) {
        Step stepById = stepRepository.findById(updateStepRequest.getStepId());
        stepService.updateStepNext(stepById);
    }

    @DoPut("/update-prev-step")
    public void updatePrevStep(@RequestBody UpdateStepRequest updateStepRequest) {
        Step stepById = stepRepository.findById(updateStepRequest.getStepId());
        stepService.updateStepPrev(stepById);
    }

    @DoGet("/get-page")
    public Map<String, Object> getPage(@RequestParam int page, @RequestParam int size) {
        int skip = size * page;
        SearchStepData searchStepData = SearchStepData.builder().size(size).skip(skip).build();
        return stepService.getStepPaging(searchStepData);
    }

}
