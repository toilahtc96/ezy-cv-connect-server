package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProgressResponse {
    private long id;
    private Long agencyId;
    private Long candidateId;
    private EntityStatus status;
    private Long stepId;
}
