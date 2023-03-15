package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.UserStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class UserRegisterData {
    private Long typeId;
    private Date birthDay;
    private String name;
    private Long levelId;
    private Integer experienceYear;
    private String cvLink;
    private String username;
    private String password;
    private UserStatus status;
    private String fullname;
}
