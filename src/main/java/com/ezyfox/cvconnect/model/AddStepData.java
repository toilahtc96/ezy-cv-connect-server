package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.StepCode;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddStepData {
    private StepCode stepCode;
    private String meaning;
    private EntityStatus status;
    private int order;
}
