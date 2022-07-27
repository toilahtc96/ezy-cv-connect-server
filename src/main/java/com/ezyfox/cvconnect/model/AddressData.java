package com.ezyfox.cvconnect.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddressData {
    private long id;
    private int type;
    private String code;
    private String name;
    private long parentId;
}
