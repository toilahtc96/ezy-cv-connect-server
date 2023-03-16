package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Data;

@Data
public class CareerRequest {
    private long id;
    private String name;
    private EntityStatus status;
}
