package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EditUserRoleData {
    private long userRoleId;
    private long userId;
    private long roleId;
    private EntityStatus status;
}
