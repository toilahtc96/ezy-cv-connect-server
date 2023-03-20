package com.ezyfox.cvconnect.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchJobData {
    private String status;
    private String information;
    private int page;
    private int size;
}
