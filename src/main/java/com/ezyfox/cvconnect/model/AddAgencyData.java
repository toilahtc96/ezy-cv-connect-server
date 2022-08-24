package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.UserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class AddAgencyData {
    private long roleId;
    private long companyId;
    private String description;
    private String information;
    private Date birthDay;
    private String name;
    private String userName;
    private String password;
    private long typeId;
    private UserStatus status;
    private int star;
}
