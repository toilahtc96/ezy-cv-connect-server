package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WorkingFormResponse {
    private long id;
    private String name;
    private String description;
    private EntityStatus status;
}
