package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchCompanyData {
    private String companyName;
    private String companyCode;
    private String provinceCode;
    private String districtCode;
    private String precinctCode;
    private EntityStatus status;
    private int star;
    private String information;
}
