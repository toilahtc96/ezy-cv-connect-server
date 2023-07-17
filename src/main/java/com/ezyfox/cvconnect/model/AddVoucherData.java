package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.VoucherType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class AddVoucherData {
    private String title;
    private VoucherType voucherType;
    private Double value;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private EntityStatus status;
}
