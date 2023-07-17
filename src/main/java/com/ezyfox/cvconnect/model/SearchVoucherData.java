package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.VoucherType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class SearchVoucherData {
    private String title;
    private VoucherType voucherType;
    private String value;
    private LocalDateTime from;
    private LocalDateTime to;
    private EntityStatus status;
    private Long createdId;
    private int page;
    private int size;
}
