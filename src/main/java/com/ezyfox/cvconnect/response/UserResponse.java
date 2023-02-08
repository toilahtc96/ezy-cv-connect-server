package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.UserStatus;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Builder
@Getter
public class UserResponse {
    private long id;
    private long roleId;
    private long companyId;
    private long typeId;
    private String description;
    private int star;
    private String information;
    private Date birthDay;
    private String name;
    private int levelId;
    private int experienceYear;
    private String cvLink;
    private String username;
    private String password;
    private UserStatus status;
}
