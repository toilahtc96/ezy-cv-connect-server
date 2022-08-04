package com.ezyfox.cvconnect.model;

import lombok.Builder;
import lombok.Getter;
import java.util.Date;

@Builder
@Getter
public class AddAgencyData {
    private int roleId;
    private int companyId;
    private String description;
    private String information;
    private Date birthDay;
    private String name;
    private String userName;
    private String password;
    private int typeId;
    private int status;
    private int star;
}
