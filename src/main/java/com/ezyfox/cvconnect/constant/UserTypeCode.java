package com.ezyfox.cvconnect.constant;

import com.tvd12.ezyfox.util.EzyEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
public enum UserTypeCode {

    AGENCY("agency"),
    CANDIDATE("candidate"),
    ;

    @Getter
    private final String name;

    private static final Map<Object, UserTypeCode> MAP =
        EzyEnums.enumMap(UserTypeCode.class, it -> it.name);
    
    public static UserTypeCode of(String name) {
        return name == null ? null : MAP.get(name);
    }

}
