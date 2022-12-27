package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.AddressType;
import lombok.Data;

@Data
public class EditAddressRequest {

    private long id;
    private long parentId;
    private String name;
    private AddressType type;
}
