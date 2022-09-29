package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EditReviewData {
    private long reviewId;
    private String description;
    private int star;
    private long objectId;
    private long reviewOwner;
    private int type;
    private EntityStatus status;
}
