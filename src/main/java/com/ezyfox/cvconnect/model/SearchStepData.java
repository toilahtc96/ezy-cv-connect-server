package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.StepCode;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Builder
public class SearchStepData {
    @Enumerated(EnumType.STRING)
    private EntityStatus status;
    @Enumerated(EnumType.STRING)
    private StepCode stepCode;
    private String meaning;
    private int size;
    private int skip;
}
