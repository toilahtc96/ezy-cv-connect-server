package com.ezyfox.cvconnect.request;

import lombok.Data;

@Data
public class UpdateCvLinkOfProcessRequest {
    private long agencyId;
    private long jobId;
    private String cvLink;
}
