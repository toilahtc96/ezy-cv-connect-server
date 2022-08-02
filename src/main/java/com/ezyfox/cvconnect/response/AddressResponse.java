package com.ezyfox.cvconnect.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddressResponse {
    private long id;
    private int type;
    private String code;
    private String name;
    private long parentId;
}
