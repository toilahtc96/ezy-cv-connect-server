package com.ezyfox.cvconnect.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddUserTypeData {
    private String code;
    private String meaning;
}
