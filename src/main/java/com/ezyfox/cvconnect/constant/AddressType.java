package com.ezyfox.cvconnect.constant;

import com.tvd12.ezyfox.util.EzyEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
public enum AddressType {

    PROVINCE("province", 1),
    DISTRICT("district", 2),
    PRECINCT("precinct", 3),
    ;

    @Getter
    private final String name;
    @Getter
    private final int value;

    private static final Map<Object, AddressType> MAP =
        EzyEnums.enumMap(AddressType.class, it -> it.value);

    public static AddressType of(String name) {
        return name == null ? null : MAP.get(name);
    }

    public static AddressType of(int value) {
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
