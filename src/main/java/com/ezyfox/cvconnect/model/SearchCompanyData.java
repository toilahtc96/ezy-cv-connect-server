package com.ezyfox.cvconnect.model;

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
    private String status;
    private int star;
    private String information;
    private int page;
    private int size;
}
