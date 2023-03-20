package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.annotation.UserId;
import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.LevelName;
import com.ezyfox.cvconnect.converter.JobRequestToDataConverter;
import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.request.AddCompanyRequest;
import com.ezyfox.cvconnect.request.AddJobRequest;
import com.ezyfox.cvconnect.request.EditCompanyRequest;
import com.ezyfox.cvconnect.request.EditJobRequest;
import com.ezyfox.cvconnect.response.CompanyResponse;
import com.ezyfox.cvconnect.response.JobResponse;
import com.ezyfox.cvconnect.service.CompanyServie;
import com.ezyfox.cvconnect.service.JobService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@Controller("api/v1/job")
@AllArgsConstructor
@Authenticated
public class JobController {

    private final JobService jobService;
    private final JobRequestToDataConverter jobRequestToDataConverter;

    @DoPost("/add")
    public ResponseEntity addJob(@RequestBody AddJobRequest addJobRequest, @UserId long userId) {
        jobService.addJob(jobRequestToDataConverter.toDataFromAddJob(addJobRequest), userId);
        return ResponseEntity.noContent();
    }

    @DoGet("/get-by-id")
    public JobResponse getById(@RequestParam long id) {
        return jobService.getById(id);
    }

    @DoPost("/edit")
    public ResponseEntity editCompany(@RequestBody EditJobRequest editJobRequest) {
        jobService.editJob(jobRequestToDataConverter.toDataFromEditJob(editJobRequest));
        return ResponseEntity.noContent();
    }

    @DoGet("/find-by-field")
    public Map<String, Object> getByField(
        @RequestParam("jobTypeId") Long jobTypeId,
        @RequestParam("companyId") Long companyId,
        @RequestParam("rangeSalary") Double rangeSalary,
        @RequestParam("levelName") LevelName levelName,
        @RequestParam("customRange") Boolean customRange,
        @RequestParam("careerId") Long careerId,
        @RequestParam("workingFormId") Long workingFormId,
        @RequestParam("information") String information,
        @RequestParam("status") EntityStatus status,
        @RequestParam("page") int page,
        @RequestParam("size") int size
    ) {
        return jobService.getByField(jobRequestToDataConverter
            .toDataFromSearchJob(
                jobTypeId,
                companyId,
                rangeSalary,
                levelName,
                customRange,
                careerId,
                workingFormId,
                information == null ? "" : information.trim(),
                status,
                page,
                size
            ));
    }
}
