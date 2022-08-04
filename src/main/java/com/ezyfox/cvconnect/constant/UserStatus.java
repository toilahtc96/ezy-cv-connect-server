package com.ezyfox.cvconnect.constant;

import com.tvd12.ezyfox.util.EzyEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
public enum UserStatus {

    ACTIVE("Active", 1),
    BLOCK("Block", 2),
    ;

    @Getter
    private final String name;
    @Getter
    private final int value;

    private static final Map<Object, UserStatus> MAP =
        EzyEnums.enumMap(UserStatus.class, it -> it.value);
    
    public static UserStatus of(String name) {
        return name == null ? null : MAP.get(name);
    }

    public static UserStatus of(int value) {
        return MAP.get(value);
    }

}
