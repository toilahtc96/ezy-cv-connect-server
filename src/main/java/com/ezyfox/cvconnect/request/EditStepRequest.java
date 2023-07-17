package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.StepCode;
import lombok.Data;

@Data
public class EditStepRequest {
    private long id;
    private StepCode code;
    private String meaning;
    private EntityStatus status;
}
