package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.LevelName;
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
    private String status;
    private String information;
    private int page;
    private int size;
}
