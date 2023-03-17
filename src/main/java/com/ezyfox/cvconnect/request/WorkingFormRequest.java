package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Data;

@Data
public class JobTypeRequest {
    private long id;
    private String name;
    private String description;
    private EntityStatus status;
}
