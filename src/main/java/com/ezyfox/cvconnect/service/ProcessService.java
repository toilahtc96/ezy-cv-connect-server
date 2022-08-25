package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.ProcessCode;
import com.ezyfox.cvconnect.entity.Process;
import com.ezyfox.cvconnect.model.AddProcessData;
import com.ezyfox.cvconnect.model.EditProcessData;
import com.ezyfox.cvconnect.response.ProcessResponse;
import com.tvd12.ezyhttp.core.response.ResponseEntity;

import java.util.List;

public interface ProcessService {

    ResponseEntity addProcess(AddProcessData addProcessData);

    ResponseEntity editProcess(EditProcessData editProcessData);

    void updateProcessNext(Process process);

    void updateProcessPrev(Process process);

    void updateProcessCode(EditProcessData editProcessData, ProcessCode processCode);

    List<ProcessResponse> getByProcessCode(ProcessCode processCode);

    List<ProcessResponse> getByStatus(EntityStatus status);

    List<ProcessResponse> getAll();

    ProcessResponse getById(long processId);
}
