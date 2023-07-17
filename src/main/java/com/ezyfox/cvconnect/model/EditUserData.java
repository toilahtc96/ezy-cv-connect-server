package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.constant.UserTypeCode;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class EditUserData {
    private long id;
    private Long roleId;
    private Long companyId;
    private String description;
    private String information;
    private Date birthDay;
    private String name;
    private String userName;
    private String password;
    private Long typeId;
    private UserStatus status;
    private Integer star;
    private String cvLink;
    private Long levelId;
    private Integer experienceYear;
    private UserTypeCode userTypeCode;
    private String avatarUrl;
}
