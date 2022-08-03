package com.ezyfox.cvconnect.request;

import lombok.Data;

@Data
public class EditUserTypeRequest {
    private long id;
    private String code;
    private String meaning;
    private int status;
}
