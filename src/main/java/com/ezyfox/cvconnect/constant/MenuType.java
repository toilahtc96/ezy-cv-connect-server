package com.ezyfox.cvconnect.constant;

import com.tvd12.ezyfox.util.EzyEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
public enum MenuType {

    LEVEL_1("LEVEL_1", 1),
    LEVEL_2("LEVEL_2", 2),
    LEVEL_3("LEVEL_3", 3),
    ;

    @Getter
    private final String name;
    @Getter
    private final int value;

    private static final Map<Object, MenuType> MAP =
        EzyEnums.enumMap(MenuType.class, it -> it.value);

    public static MenuType of(String name) {
        return name == null ? null : MAP.get(name);
    }

    public static MenuType of(int value) {
        return MAP
            .entrySet()
            .stream()
            .filter(
                item -> item.getValue().getValue() == value
            )
            .findAny()
            .orElseThrow(IllegalAccessError::new)
            .getValue();
    }
}
