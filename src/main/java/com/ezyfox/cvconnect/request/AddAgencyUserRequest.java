package com.ezyfox.cvconnect.request;

import lombok.Data;

@Data
public class AddAgencyUserRequest {
    private long roleId;
    private long companyId;
    private String description;
    private String information;
    private String birthDay;
    private String name;
    private String userName;
    private String password;
}
