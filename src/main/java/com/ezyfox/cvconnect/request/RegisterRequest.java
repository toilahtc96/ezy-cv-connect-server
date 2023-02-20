package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.UserStatus;
import lombok.Data;

@Data
public class RegisterRequest {

    private String username;
    private String password;
}
