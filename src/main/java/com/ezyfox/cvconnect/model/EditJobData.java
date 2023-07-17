package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EditJobData {
    private long id;
    private Long jobTypeId;
    private Long companyId;
    private Long levelId;
    private Long careerId;
    private Long workingFormId;
    private Integer quantity;
    private Double rangeSalaryMin;
    private Double rangeSalaryMax;
    private String information;
    private EntityStatus status;
    private Boolean customRange;
    private String thumbnail;
    private String title;
    private Long voucherId;
    private String tags;
    private String reasonForChoosing;
}
