package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Data;

@Data
public class AddUserTypeRequest {
    private String code;
    private String meaning;
    private EntityStatus status;
}
