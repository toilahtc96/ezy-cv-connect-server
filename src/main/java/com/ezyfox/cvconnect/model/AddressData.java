package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.AddressType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddressData {
    private long id;
    private AddressType type;
    private String code;
    private String name;
    private long parentId;
}
