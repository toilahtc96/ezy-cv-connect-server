package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Data;

@Data
public class EditProgressRequest {
    private long id;
    private Long agencyId;
    private Long candidateId;
    private EntityStatus status;
    private Long stepId;
}
