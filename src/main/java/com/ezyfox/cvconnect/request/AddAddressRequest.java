package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.AddressType;
import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Data;

@Data
public class AddAddressRequest {
    private String provinceCode;
    private String provinceName;
    private String districtCode;
    private String districtName;
    private String precinctCode;
    private String precinctName;
    private AddressType type;
    private EntityStatus status;
}
