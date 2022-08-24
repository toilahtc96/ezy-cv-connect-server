package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.RoleCode;
import lombok.Data;

@Data
public class RoleRequest {
    private long id;
    private String name;
    private RoleCode code;
    private EntityStatus status;
}
