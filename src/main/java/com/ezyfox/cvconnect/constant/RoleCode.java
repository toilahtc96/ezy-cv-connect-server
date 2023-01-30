package com.ezyfox.cvconnect.constant;

import com.tvd12.ezyfox.util.EzyEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
public enum RoleCode {

    AGENCY("AGENCY"),
    CANDIDATE("CANDIDATE"),
    ADMIN("ADMIN")
    ;

    @Getter
    private final String name;

    private static final Map<Object, RoleCode> MAP =
        EzyEnums.enumMap(RoleCode.class, it -> it.name);
    
    public static RoleCode of(String name) {
        return name == null ? null : MAP.get(name);
    }

}
