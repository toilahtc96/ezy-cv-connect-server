package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.RoleCode;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RoleData {
    private long id;
    private RoleCode code;
    private String name;
    private EntityStatus status;
}
