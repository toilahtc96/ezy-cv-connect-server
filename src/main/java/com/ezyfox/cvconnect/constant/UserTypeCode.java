package com.ezyfox.cvconnect.constant;

import com.tvd12.ezyfox.util.EzyEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
public enum UserTypeCode {

    AGENCY("agency"),
    CANDIDATE("candidate"),
    ADMIN("admin")
    ;

    @Getter
    private final String name;

}
