package com.ezyfox.cvconnect.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum VoucherType {
    PERCENT("percent"),
    CASH("cash");
    @Getter
    private final String name;
}
