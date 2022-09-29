package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.RoleCode;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddRoleData {
    private RoleCode code;
    private String name;
}
