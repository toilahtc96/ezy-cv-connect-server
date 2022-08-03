package com.ezyfox.cvconnect.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EditUserTypeData {
    private long id;
    private String code;
    private String meaning;
    private int status;
}
