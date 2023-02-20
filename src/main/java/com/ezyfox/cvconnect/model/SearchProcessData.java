package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.ProcessCode;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Builder
public class SearchProcessData {
    @Enumerated(EnumType.STRING)
    private EntityStatus status;
    @Enumerated(EnumType.STRING)
    private ProcessCode processCode;
    private String meaning;
    private int size;
    private int skip;
}
