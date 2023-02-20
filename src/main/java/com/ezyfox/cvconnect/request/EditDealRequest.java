package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Data;

@Data
public class EditDealRequest {
    private long id;
    private Long agencyId;
    private Long candidateId;
    private EntityStatus status;
    private Long processId;
}
