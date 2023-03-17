package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.converter.JobTypeRequestToDataConverter;
import com.ezyfox.cvconnect.model.AddJobTypeData;
import com.ezyfox.cvconnect.model.JobTypeData;
import com.ezyfox.cvconnect.request.AddJobTypeRequest;
import com.ezyfox.cvconnect.request.JobTypeRequest;
import com.ezyfox.cvconnect.response.JobTypeResponse;
import com.ezyfox.cvconnect.service.JobTypeService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

@Controller("api/v1/job-type")
@AllArgsConstructor
@Authenticated
public class JobTypeController {

    private final JobTypeService jobTypeService;
    private final JobTypeRequestToDataConverter jobTypeRequestToDataConverter;

    @DoPost("/add")
    public ResponseEntity addJobType(@RequestBody AddJobTypeRequest addJobTypeRequest) {
        AddJobTypeData addJobTypeData = jobTypeRequestToDataConverter.toDataFromAddJobType(addJobTypeRequest);
        jobTypeService.addJobType(addJobTypeData);
        return ResponseEntity.noContent();
    }

    @DoPost("/edit")
    public ResponseEntity editJobType(@RequestBody JobTypeRequest editJobTypeRequest) {
        JobTypeData jobTypeData = jobTypeRequestToDataConverter.toDataFromEditJobType(editJobTypeRequest);
        jobTypeService.editJobType(jobTypeData);
        return ResponseEntity.noContent();
    }

    @DoGet("/get-by-id")
    public JobTypeResponse getById(@RequestParam long id) {
        return jobTypeService.getById(id);
    }

    @DoGet("/get-by-code")
    public List<JobTypeResponse> getByCode(@RequestParam String code) {
        return jobTypeService.getJobTypeByCodeActive(code);
    }

    @DoGet("/get-by-name")
    public List<JobTypeResponse> getByName(@RequestParam String name) {
        return jobTypeService.getJobTypeByNameActive(name);
    }

    @DoGet("/get-all-active")
    public List<JobTypeResponse> getAllActive() {
        return jobTypeService.getAllJobTypeActive();
    }

    @DoGet("/get-all")
    public List<JobTypeResponse> getAll() {
        return jobTypeService.getAllJobType();
    }

    @DoGet("/get-page")
    public List<JobTypeResponse> getPage(@RequestParam int page, @RequestParam int size) {
        return jobTypeService.getPaging(page, size);
    }
}
