package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.AddressType;
import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Data;

@Data
public class AddAddressRequest {
    private long parentId;
    private String name;
    private AddressType type;
    private EntityStatus status;
}
