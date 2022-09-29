package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Data;

@Data
public class EditCompanyRequest {
    private long companyId;
    private String code;
    private String provinceCode;
    private String districtCode;
    private String precinctCode;
    private String information;
    private String name;
    private int star;
    private EntityStatus status;
}
