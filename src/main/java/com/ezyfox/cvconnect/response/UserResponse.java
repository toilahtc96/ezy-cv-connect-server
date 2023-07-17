package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.LevelName;
import com.ezyfox.cvconnect.constant.RoleCode;
import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.constant.UserTypeCode;
import lombok.Builder;
import lombok.Getter;

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
    private Long levelId;
    private Integer experienceYear;
    private String cvLink;
    private String username;
    private String password;
    private UserStatus status;
    private Long companyId;
    private UserTypeCode userTypeCode;
    private LevelName level;
    private String avatarUrl;
}
