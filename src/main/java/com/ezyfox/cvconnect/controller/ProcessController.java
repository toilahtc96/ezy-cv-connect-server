package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.ProcessCode;
import com.ezyfox.cvconnect.converter.EntityToDataConverter;
import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.entity.Process;
import com.ezyfox.cvconnect.model.AddProcessData;
import com.ezyfox.cvconnect.model.EditProcessData;
import com.ezyfox.cvconnect.model.SearchProcessData;
import com.ezyfox.cvconnect.repository.ProcessRepository;
import com.ezyfox.cvconnect.request.AddProcessRequest;
import com.ezyfox.cvconnect.request.EditProcessRequest;
import com.ezyfox.cvconnect.request.UpdateProcessRequest;
import com.ezyfox.cvconnect.response.ProcessResponse;
import com.ezyfox.cvconnect.service.ProcessService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@Controller("api/v1/process")
@AllArgsConstructor
@Authenticated
public class ProcessController {

    private final ProcessService processService;
    private final ProcessRepository processRepository;
    private final RequestToDataConverter requestToDataConverter;
    private final EntityToDataConverter entityToDataConverter;

    @DoPost("/add")
    public ResponseEntity addProcess(@RequestBody AddProcessRequest addProcessRequest) {
        AddProcessData addProcessData = requestToDataConverter.toDataFromAddProcess(addProcessRequest);
        processService.addProcess(addProcessData);
        return ResponseEntity.noContent();
    }

    @DoPost("/edit")
    public ResponseEntity editAddress(@RequestBody EditProcessRequest editProcessRequest) {
        EditProcessData addProcessData = requestToDataConverter.toDataFromEditProcess(editProcessRequest);
        processService.editProcess(addProcessData);
        return ResponseEntity.noContent();
    }

    @DoGet("/code")
    public List<ProcessResponse> getByCode(@RequestParam(name = "code") ProcessCode processCode) {
        return processService.getByProcessCode(processCode);
    }

    @DoGet("/status")
    public List<ProcessResponse> getByStatus(@RequestParam EntityStatus status) {
        return processService.getByStatus(status);
    }

    @DoGet("/get-all")
    public List<ProcessResponse> getAll() {
        return processService.getAll();
    }

    @DoGet("/get-by-id")
    public ProcessResponse getById(@RequestParam long id) {
        return processService.getById(id);
    }

    @DoPut("/update-next-process")
    public void updateNextProcess(@RequestBody UpdateProcessRequest updateProcessRequest) {
        Process processById = processRepository.findById(updateProcessRequest.getProcessId());
        processService.updateProcessNext(processById);
    }

    @DoPut("/update-prev-process")
    public void updatePrevProcess(@RequestBody UpdateProcessRequest updateProcessRequest) {
        Process processById = processRepository.findById(updateProcessRequest.getProcessId());
        processService.updateProcessPrev(processById);
    }

    @DoGet("/get-page")
    public Map<String, Object> getPage(@RequestParam int page, @RequestParam int size) {
        int skip = size * page;
        SearchProcessData searchProcessData = SearchProcessData.builder().size(size).skip(skip).build();
        return processService.getProcessPaging(searchProcessData);
    }

}
