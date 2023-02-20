package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserTypeResponse {
    private long id;
    private String code;
    private String meaning;
    private EntityStatus status;
}
