package com.ezyfox.cvconnect.constant;

import com.tvd12.ezyfox.util.EzyEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
public enum LevelName {
    JUNIOR("Junior", 1),
    SENIOR("senior", 2),
    LEADER("leader", 3),
    MID_MANAGER("Mid Manager", 4),
    SENIOR_MANAGER("Senior Manager", 5),
    ;

    @Getter
    private final String name;
    @Getter
    private final int value;

    private static final Map<Object, LevelName> MAP =
        EzyEnums.enumMap(LevelName.class, it -> it.value);

    public static LevelName of(String name) {
        return name == null ? null : MAP.get(name);
    }

    public static LevelName of(int value) {
        return MAP.get(value);
    }

}
