package com.ezyfox.cvconnect.request;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String fullname;
    private String username;
    private String password;
    private Boolean isCondition;
    private String rePassword;
}
