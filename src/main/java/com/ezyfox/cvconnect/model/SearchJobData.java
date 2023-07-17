package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchJobData {
    private Long jobTypeId;
    private Long companyId;
    private Double rangeSalary;
    private Long levelId;
    private Boolean customRange;
    private Long careerId;
    private Long workingFormId;
    private EntityStatus status;
    private String information;
    private String tag;
    private int page;
    private int size;
}
