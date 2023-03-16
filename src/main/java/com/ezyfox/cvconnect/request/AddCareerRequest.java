package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Data;

@Data
public class AddCareerRequest {
    private long id;
    private String name;
    private EntityStatus status;
}
