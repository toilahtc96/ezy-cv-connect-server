package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.LevelName;
import lombok.Getter;

@Getter
public class AddJobRequest {
    private Long jobTypeId;
    private Long companyId;
    private LevelName levelName;
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
