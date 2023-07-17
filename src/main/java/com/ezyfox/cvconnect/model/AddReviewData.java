package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.ReviewType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddReviewData {
    private String description;
    private int star;
    private long objectId;
    private ReviewType type;
}
