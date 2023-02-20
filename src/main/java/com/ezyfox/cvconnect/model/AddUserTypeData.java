package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddUserTypeData {
    private String code;
    private String meaning;
    private EntityStatus status;
}
