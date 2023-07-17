package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.VoucherType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EditVoucherRequest {
    private long id;
    private String title;
    private VoucherType voucherType;
    private Double value;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private EntityStatus status;
}
