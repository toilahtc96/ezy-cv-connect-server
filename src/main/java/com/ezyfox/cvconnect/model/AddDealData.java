package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddDealData {
    private long agencyId;
    private long candidateId;
    private EntityStatus status;
    private long processId;
}
