package com.ezyfox.cvconnect.request;

import lombok.Data;

@Data
public class AddCompanyRequest {
    private String code;
    private String provinceCode;
    private String districtCode;
    private String precinctCode;
    private String information;
    private String name;
    private int star;
}
