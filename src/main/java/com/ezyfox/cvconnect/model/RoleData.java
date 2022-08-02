package com.ezyfox.cvconnect.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RoleData {
    private long id;
    private String code;
    private String name;
    private int status;
}
