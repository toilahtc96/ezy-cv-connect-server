package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DealResponse {
    private long dealId;
    private long agencyId;
    private long candidateId;
    private EntityStatus status;
    private long processId;
}
