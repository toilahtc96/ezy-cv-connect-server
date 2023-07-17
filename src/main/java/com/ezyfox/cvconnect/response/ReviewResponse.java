package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.ReviewType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Builder
public class ReviewResponse {
    private long id;
    private String description;
    private int star;
    private long objectId;
    private long reviewOwner;
    @Enumerated(EnumType.ORDINAL)
    private ReviewType type;
    private EntityStatus status;
    private String reviewOwnerName;
    private String reviewOwnerAvatar;
}
