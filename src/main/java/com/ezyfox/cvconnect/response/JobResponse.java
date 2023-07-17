package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.LevelName;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JobResponse {
    private long id;
    private String information;
    private EntityStatus status;
    private Long jobTypeId;
    private String jobTypeName;
    private Long companyId;
    private String companyName;
    private Integer quantity;
    private Double rangeSalaryMin;
    private Double rangeSalaryMax;
    private LevelName levelName;
    private Boolean customRange;
    private Long careerId;
    private String careerName;
    private Long workingFormId;
    private String workingFormName;
    private Long createdId;
    private String thumbnail;
    private String title;
    private Long voucherId;
    private String voucherTitle;
    private String voucherInfo;
    private Long agencyId;
    private String agencyName;
    private String avatarUrl;
    private String tags;
    private String reasonForChoosing;
}
