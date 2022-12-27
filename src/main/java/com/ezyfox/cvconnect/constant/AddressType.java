package com.ezyfox.cvconnect.constant;

import com.tvd12.ezyfox.util.EzyEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
public enum AddressType {

    PROVINCE("PROVINCE", "PROVINCE"),
    DISTRICT("DISTRICT", "DISTRICT"),
    PRECINCT("PRECINCT", "PRECINCT"),
    ;

    @Getter
    private final String name;
    @Getter
    private final String value;

    private static final Map<Object, AddressType> MAP =
        EzyEnums.enumMap(AddressType.class, it -> it.value);

    public static AddressType of(String name) {
        return name == null ? null : MAP.get(name);
    }
}
