package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Data;

@Data
public class AddProgressRequest {
    private long agencyId;
    private long candidateId;
    private EntityStatus status;
    private Long stepId;
}
