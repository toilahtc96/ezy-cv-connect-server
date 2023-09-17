package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProgressResponse {
    private long id;
    private Long agencyId;
    private Long candidateId;
    private EntityStatus status;
    private Long stepId;
    private String candidateName;
    private String agencyName;
    private String levelName;
    private String companyName;
    private String careerName;
    private String createdDate;
    private String cvLink;
    private String sendCvCompanyTime;
}
