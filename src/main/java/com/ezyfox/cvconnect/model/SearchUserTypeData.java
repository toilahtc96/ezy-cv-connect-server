package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchUserTypeData {
    private String meaning;
    private String code;
    private EntityStatus status;
    private int size;
    private int skip;
}
