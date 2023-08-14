package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EditProgressData {
    private long id;
    private long agencyId;
    private long candidateId;
    private EntityStatus status;
    private Long stepId;
}