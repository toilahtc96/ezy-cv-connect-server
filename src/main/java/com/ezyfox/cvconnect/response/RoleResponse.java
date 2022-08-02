package com.ezyfox.cvconnect.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RoleResponse {
    private long id;
    private String code;
    private String name;
    private int status;
}
