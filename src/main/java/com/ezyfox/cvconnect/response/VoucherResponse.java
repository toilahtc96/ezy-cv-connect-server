package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.VoucherType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VoucherResponse {
    private Long id;
    private String title;
    private VoucherType voucherType;
    private Double value;
    private String startTime;
    private String endTime;
    private EntityStatus status;
}
