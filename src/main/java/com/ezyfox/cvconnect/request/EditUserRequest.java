package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.LevelName;
import com.ezyfox.cvconnect.constant.RoleCode;
import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.constant.UserTypeCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.Nullable;
import lombok.Getter;

@Getter
public class EditUserRequest {
    private int id;
    @Nullable
    private RoleCode roleId;
    @Nullable
    private Long companyId;
    @Nullable
    private Long typeId;
    @Nullable
    private String description;
    @Nullable
    private int star;
    @Nullable
    private LevelName level;
    @Nullable
    private String information;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT")
    @Nullable
    private String birthDay;
    private String name;
    private String username;
    @Nullable
    private String password;
    @Nullable
    private UserStatus status;
    @Nullable
    private String cvLink;
    @Nullable
    private Integer experienceYear;
    @Nullable
    private UserTypeCode userTypeCode;
    @Nullable
    private String avatarUrl;
}
