package com.ezyfox.cvconnect.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddAddressData {
    private String name;
    private int type;
    private long parentId;
}
