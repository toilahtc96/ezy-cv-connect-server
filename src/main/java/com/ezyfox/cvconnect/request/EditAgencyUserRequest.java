package com.ezyfox.cvconnect.request;

import lombok.Getter;

@Getter
public class EditAgencyUserRequest {
    private int id;
    private int roleId;
    private int companyId;
    private int typeId;
    private String description;
    private int star;
    private String information;
    private String birthDay;
    private String name;
    private String userName;
    private String password;
    private int status;
}
