package com.ezyfox.cvconnect.request;

import lombok.Data;

@Data
public class AddUserRoleRequest {
    private long userId;
    private long roleId;
}
