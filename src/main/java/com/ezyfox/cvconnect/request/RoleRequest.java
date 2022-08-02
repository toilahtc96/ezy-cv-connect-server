package com.ezyfox.cvconnect.request;

import lombok.Data;

@Data
public class RoleRequest {
    private long id;
    private String name;
    private String code;
    private int status;
}
