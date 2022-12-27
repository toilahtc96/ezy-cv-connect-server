package com.ezyfox.cvconnect.constant;

import com.tvd12.ezyfox.util.EzyEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
public enum UserStatus {

    ACTIVED("ACTIVED"),
    DISABLED("DISABLED"),
    ;

    @Getter
    private final String name;

    private static final Map<Object, UserStatus> MAP =
        EzyEnums.enumMap(UserStatus.class, it -> it.name);
    
    public static UserStatus of(String name) {
        return name == null ? null : MAP.get(name);
    }

}
