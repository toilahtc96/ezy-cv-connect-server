package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.request.AddDealRequest;
import com.ezyfox.cvconnect.request.EditDealRequest;
import com.ezyfox.cvconnect.response.DealResponse;
import com.ezyfox.cvconnect.service.DealService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

@Controller("api/v1/deal")
@AllArgsConstructor
@Authenticated
public class DealController {

    private final DealService dealService;
    private final RequestToDataConverter requestToDataConverter;

    @DoPost("/add")
    public ResponseEntity addDeal(@RequestBody AddDealRequest addDealRequest) {
        dealService.addDeal(requestToDataConverter.toDataFromAddDeal(addDealRequest));
        return ResponseEntity.noContent();
    }

    @DoPost("/edit")
    public ResponseEntity editDeal(@RequestBody EditDealRequest editDealRequest) {
        dealService.editDeal(requestToDataConverter.toDataFromEditDeal(editDealRequest));
        return ResponseEntity.noContent();
    }

    @DoGet("/agency")
    public List<DealResponse> getByAgencyId(@RequestParam long agencyId) {
        return dealService.getByAgencyId(agencyId);
    }

    @DoGet("/candidate")
    public List<DealResponse> getByCandidateId(@RequestParam long candidateId) {
        return dealService.getByCandidateId(candidateId);
    }

    @DoGet("/get-all")
    public List<DealResponse> getAll() {
        return dealService.getAll();
    }

    @DoGet("/get-active-of-agency")
    public List<DealResponse> getActiveOfAgency(@RequestParam long agencyId) {
        return dealService.getAllActiveOfAgency(agencyId);
    }
}
