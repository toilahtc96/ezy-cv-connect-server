package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.RoleCode;
import lombok.Data;

@Data
public class AddRoleRequest {
    private String name;
    private RoleCode code;
}
