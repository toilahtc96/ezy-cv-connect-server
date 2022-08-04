package com.ezyfox.cvconnect.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserAgencyResponse {

    private int id;
    private int companyId;
    private String description;
    private String information;
    private String birthDay;
    private String name;
    private String userName;
    private String password;
    private int star;
    private int typeId;
    private int status;
}
