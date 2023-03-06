package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.LevelName;
import com.ezyfox.cvconnect.constant.RoleCode;
import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.constant.UserTypeCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Date;

@Getter
public class EditUserRequest {
    private int id;
    private RoleCode roleId;
    private Long companyId;
    private Long typeId;
    private String description;
    private int star;
    private LevelName level;
    private String information;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="GMT")
    private String birthDay;
    private String name;
    private String userName;
    private String password;
    private UserStatus status;
    private String cvLink;
    private Integer experienceYear;
    private UserTypeCode userTypeCode;
}
