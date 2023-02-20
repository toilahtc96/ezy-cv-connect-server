package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Builder
public class SearchDealData {
    @Enumerated(EnumType.STRING)
    private EntityStatus status;
    private Long agencyId;
    private Long candidateId;
    private Long processId;
    private int size;
    private int skip;
}
