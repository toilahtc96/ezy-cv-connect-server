package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Data;

@Data
public class RoleRequest {
    private long id;
    private String name;
    private String code;
    private EntityStatus status;
}
