package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.StepCode;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StepResponse {
    private long id;
    private StepCode code;
    private String meaning;
    private EntityStatus status;
}
