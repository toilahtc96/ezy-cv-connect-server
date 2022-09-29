package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.ProcessCode;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProcessResponse {
    private long processId;
    private ProcessCode code;
    private String meaning;
    private EntityStatus status;
}
