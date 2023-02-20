package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.RoleCode;
import com.ezyfox.cvconnect.constant.UserStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class UserResponse {
    private long id;
    private RoleCode roleName;
    private String companyName;
    private Long typeId;
    private String description;
    private Integer star;
    private String information;
    private String birthDay;
    private String name;
    private Integer levelId;
    private Integer experienceYear;
    private String cvLink;
    private String username;
    private String password;
    private UserStatus status;
    private Long companyId;
}
