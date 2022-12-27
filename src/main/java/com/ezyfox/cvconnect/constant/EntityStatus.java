package com.ezyfox.cvconnect.constant;

import com.tvd12.ezyfox.util.EzyEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
public enum EntityStatus {

    ACTIVED("ACTIVED"),
    DISABLED("DISABLED"),
    ;

    @Getter
    private final String name;

    private static final Map<Object, EntityStatus> MAP =
        EzyEnums.enumMap(EntityStatus.class, it -> it.name);
    
    public static EntityStatus of(String name) {
        return name == null ? null : MAP.get(name);
    }

}
