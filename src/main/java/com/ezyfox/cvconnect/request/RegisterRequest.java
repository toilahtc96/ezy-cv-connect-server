package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.UserStatus;
import lombok.Data;

import java.util.Date;

@Data
public class RegisterRequest {
    private Integer typeId;
    private String birthDay;
    private String name;
    private Integer levelId;
    private Integer yearExperience;
    private String cvLink;
    private String username;
    private String password;
    private UserStatus status;
}
