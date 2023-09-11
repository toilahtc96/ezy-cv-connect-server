package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.annotation.UserId;
import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.model.SearchProgressData;
import com.ezyfox.cvconnect.request.AddProgressRequest;
import com.ezyfox.cvconnect.request.EditProgressRequest;
import com.ezyfox.cvconnect.request.UpdateCvLinkOfProcessRequest;
import com.ezyfox.cvconnect.response.ProgressResponse;
import com.ezyfox.cvconnect.service.ProgressService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@Controller("api/v1/progress")
@AllArgsConstructor
@Authenticated
public class ProgressController {

    private final ProgressService progressService;
    private final RequestToDataConverter requestToDataConverter;

    @DoPost("/add")
    public ResponseEntity addProgress(@RequestBody AddProgressRequest addProgressRequest) {
        progressService.addProgress(requestToDataConverter.toDataFromAddProgress(addProgressRequest));
        return ResponseEntity.noContent();
    }

    @DoPost("/edit")
    public ResponseEntity editProgress(@RequestBody EditProgressRequest editProgressRequest) {
        progressService.editProgress(requestToDataConverter.toDataFromEditProgress(editProgressRequest));
        return ResponseEntity.noContent();
    }

    @DoGet("/agency")
    public List<ProgressResponse> getByAgencyId(@RequestParam long agencyId) {
        return progressService.getByAgencyId(agencyId);
    }

    @DoGet("/candidate")
    public List<ProgressResponse> getByCandidateId(@RequestParam long candidateId) {
        return progressService.getByCandidateId(candidateId);
    }

    @DoGet("/get-all")
    public List<ProgressResponse> getAll() {
        return progressService.getAll();
    }

    @DoGet("/get-active-of-agency")
    public List<ProgressResponse> getActiveOfAgency(@RequestParam long agencyId) {
        return progressService.getAllActiveOfAgency(agencyId);
    }

    @DoGet("/get-page")
    public Map<String, Object> getPage(@RequestParam int page, @RequestParam int size) {
        int skip = size * page;
        SearchProgressData searchProgressData = SearchProgressData.builder().size(size).skip(skip).build();
        return progressService.getProgressPaging(searchProgressData);
    }

    @DoGet("/get-by-id")
    public ProgressResponse getById(@RequestParam long id) {
        return progressService.getById(id);
    }

    @DoGet("/get-by-candidate-job")
    public List<ProgressResponse> getAllProgressDetail(@RequestParam("jobIb") long jobIb, @UserId long candidateId) {
        return progressService.getByCandidateJob(candidateId, jobIb);
    }

    @DoPost("/update-cv-by-candidate-agency-job")
    public ResponseEntity getProgressOfJob( @UserId long candidateId, @RequestBody UpdateCvLinkOfProcessRequest updateCvLinkOfProcessRequest) {
        progressService.updateCvLink(candidateId, updateCvLinkOfProcessRequest.getAgencyId(), updateCvLinkOfProcessRequest.getJobId(), updateCvLinkOfProcessRequest.getCvLink());
        return ResponseEntity.noContent();
    }
}
