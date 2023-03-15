package com.ezyfox.cvconnect.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UserTypeCode {

    AGENCY("agency"),
    CANDIDATE("candidate"),
    ADMIN("admin")
    ;

    @Getter
    private final String name;

}
