package com.ezyfox.cvconnect.request;

import lombok.Data;

@Data
public class AddReviewRequest {
    private String description;
    private int star;
    private long objectId;
    private long reviewOwner;
    private int type;
}
