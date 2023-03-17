package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.converter.WorkingFormRequestToDataConverter;
import com.ezyfox.cvconnect.model.AddWorkingFormData;
import com.ezyfox.cvconnect.model.WorkingFormData;
import com.ezyfox.cvconnect.request.AddWorkingFormRequest;
import com.ezyfox.cvconnect.request.WorkingFormRequest;
import com.ezyfox.cvconnect.response.WorkingFormResponse;
import com.ezyfox.cvconnect.service.WorkingFormService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("api/v1/working-form")
@AllArgsConstructor
@Authenticated
public class WorkingFormController {

    private final WorkingFormService workingFormService;
    private final WorkingFormRequestToDataConverter workingFormRequestToDataConverter;

    @DoPost("/add")
    public ResponseEntity addWorkingForm(@RequestBody AddWorkingFormRequest addWorkingFormRequest) {
        AddWorkingFormData addWorkingFormData = workingFormRequestToDataConverter.toDataFromAddWorkingForm(addWorkingFormRequest);
        workingFormService.addWorkingForm(addWorkingFormData);
        return ResponseEntity.noContent();
    }

    @DoPost("/edit")
    public ResponseEntity editWorkingForm(@RequestBody WorkingFormRequest editWorkingFormRequest) {
        WorkingFormData workingFormData = workingFormRequestToDataConverter.toDataFromEditWorkingForm(editWorkingFormRequest);
        workingFormService.editWorkingForm(workingFormData);
        return ResponseEntity.noContent();
    }

    @DoGet("/get-by-id")
    public WorkingFormResponse getById(@RequestParam long id) {
        return workingFormService.getById(id);
    }

    @DoGet("/get-by-code")
    public List<WorkingFormResponse> getByCode(@RequestParam String code) {
        return workingFormService.getWorkingFormByCodeActive(code);
    }

    @DoGet("/get-by-name")
    public List<WorkingFormResponse> getByName(@RequestParam String name) {
        return workingFormService.getWorkingFormByNameActive(name);
    }

    @DoGet("/get-all-active")
    public List<WorkingFormResponse> getAllActive() {
        return workingFormService.getAllWorkingFormActive();
    }

    @DoGet("/get-all")
    public List<WorkingFormResponse> getAll() {
        return workingFormService.getAllWorkingForm();
    }

    @DoGet("/get-page")
    public List<WorkingFormResponse> getPage(@RequestParam int page, @RequestParam int size) {
        return workingFormService.getPaging(page, size);
    }

    @DoGet("/get-by-field")
    public Map<String, Object> getByField(
        @RequestParam("name") String name,
        @RequestParam("page") int page,
        @RequestParam("size") int size
    ) {
        return workingFormService.getByField(
            workingFormRequestToDataConverter
                .toDataFromSearchWorkingForm(
                    name == null ? "" : name.trim(),
                    page,
                    size
                )
        );
    }
}
