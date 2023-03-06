package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.AddressType;
import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddressResponse {
    private long id;
    private AddressType type;
    private String code;
    private String name;
    private long parentId;
    private String parentName;
    private EntityStatus status;
}
