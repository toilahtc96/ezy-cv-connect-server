package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Data;

@Data
public class EditReviewRequest {
    private long reviewId;
    private String description;
    private int star;
    private long objectId;
    private long reviewOwner;
    private int type;
    private EntityStatus status;
}
