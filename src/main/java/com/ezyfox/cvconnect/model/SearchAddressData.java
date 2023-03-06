package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchAddressData {
    private String name;
    private EntityStatus status;
    private int page;
    private int size;
}
