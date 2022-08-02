package com.ezyfox.cvconnect.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddRoleData {
    private String code;
    private String name;
}
