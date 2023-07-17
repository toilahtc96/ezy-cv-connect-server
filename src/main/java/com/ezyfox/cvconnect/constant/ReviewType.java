package com.ezyfox.cvconnect.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ReviewType {
    REVIEW_COMPANY(0),
    REVIEW_USER(1),
    REVIEW_JOB(2);

    @Getter
    private final long value;
}
