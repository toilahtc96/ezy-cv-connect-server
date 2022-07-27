package com.ezyfox.cvconnect.request;

import lombok.Data;

@Data
public class AddAddressRequest {
    private long parentId;
    private String name;
    private int type;
}
