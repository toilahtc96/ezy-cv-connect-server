package com.ezyfox.cvconnect.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum UserStatus {

    ACTIVE(1),
    BLOCK(2),
    ;
    private int value;

    public static UserStatus of(int value) {
        return Stream.of(UserStatus.values())
                .filter(p -> p.getValue() == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
