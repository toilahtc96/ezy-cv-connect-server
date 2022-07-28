package com.ezyfox.cvconnect.request;

import lombok.Data;

@Data
public class EditAddressRequest {

    private long id;
    private long parentId;
    private String name;
    private int type;
}
