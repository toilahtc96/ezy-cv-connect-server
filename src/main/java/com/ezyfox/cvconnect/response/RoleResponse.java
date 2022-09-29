package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.RoleCode;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RoleResponse {
    private long id;
    private RoleCode code;
    private String name;
    private EntityStatus status;
}
