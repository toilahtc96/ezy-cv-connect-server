package com.ezyfox.cvconnect.constant;

import com.tvd12.ezyfox.util.EzyEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
public enum EntityStatus {

    ACTIVED,
    DISABLED,
    ;


    private static final Map<Object, EntityStatus> MAP =
        EzyEnums.enumMap(EntityStatus.class, it -> it);
    
    public static EntityStatus of(String name) {
        return name == null ? null : MAP.get(name);
    }

}
