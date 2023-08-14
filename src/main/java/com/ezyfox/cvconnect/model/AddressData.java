package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.AddressType;
import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddressData {
    private long id;
    private AddressType type;
    private String provinceCode;
    private String provinceName;
    private String districtCode;
    private String districtName;
    private String precinctCode;
    private String precinctName;
    private EntityStatus status;
}
