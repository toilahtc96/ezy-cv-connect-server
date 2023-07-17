package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.VoucherType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FindVoucherRequest {
    private String title;
    private VoucherType voucherType;
    private Double value;
    private LocalDateTime from;
    private LocalDateTime to;
    private EntityStatus status;
}
