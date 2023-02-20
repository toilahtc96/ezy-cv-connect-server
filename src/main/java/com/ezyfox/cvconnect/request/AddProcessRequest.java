package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.ProcessCode;
import lombok.Data;

@Data
public class AddProcessRequest {
    private ProcessCode code;
    private String meaning;
    private EntityStatus status;
}
