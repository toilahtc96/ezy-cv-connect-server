package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {
    private Integer typeId;
    private Date birthDay;
    private String name;
    private Integer levelId;
    private Integer yearExperience;
    private String cvLink;
    private String username;
    private String password;
    private UserStatus status;
}
