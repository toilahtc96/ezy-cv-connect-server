package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.ReviewType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class AddReviewRequest {
    private String description;
    private int star;
    private long objectId;
    @Enumerated(EnumType.ORDINAL)
    private ReviewType type;
    private EntityStatus status;
}
