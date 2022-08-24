package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserRoleResponse {
    private long userRoleId;
    private long userId;
    private long roleId;
    private EntityStatus status;
}
