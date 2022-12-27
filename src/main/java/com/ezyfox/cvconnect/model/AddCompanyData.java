package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddCompanyData {
    private String code;
    private String provinceCode;
    private String districtCode;
    private String precinctCode;
    private String information;
    private String name;
    private int star;
    private EntityStatus status;
}
