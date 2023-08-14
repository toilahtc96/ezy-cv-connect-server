package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.AddressType;
import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Getter
public class SearchAddressData {
    private String provinceCode;
    private String provinceName;
    private String districtCode;
    private String districtName;
    private String precinctCode;
    private String precinctName;
    @Enumerated(EnumType.STRING)
    private AddressType type;
    @Enumerated(EnumType.STRING)
    private EntityStatus status;
    private int page;
    private int size;
}
