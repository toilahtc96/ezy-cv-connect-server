package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Data;

@Data
public class EditUserRoleRequest {
    private long userRoleId;
    private long userId;
    private long roleId;
    private EntityStatus status;
}
