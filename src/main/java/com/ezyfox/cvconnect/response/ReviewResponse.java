package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewResponse {
    private long id;
    private String description;
    private int star;
    private long objectId;
    private long reviewOwner;
    private int type;
    private EntityStatus status;
}
