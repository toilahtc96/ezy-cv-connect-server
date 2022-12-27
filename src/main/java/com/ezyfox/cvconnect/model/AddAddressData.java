package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.AddressType;
import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddAddressData {
    private String name;
    private AddressType type;
    private long parentId;
    private EntityStatus status;
}
