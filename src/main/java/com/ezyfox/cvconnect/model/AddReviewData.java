package com.ezyfox.cvconnect.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddReviewData {
    private String description;
    private int star;
    private long objectId;
    private long reviewOwner;
    private int type;
}
