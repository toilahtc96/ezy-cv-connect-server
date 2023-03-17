package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddWorkingFormData {
    private String name;
    private String description;
    private EntityStatus status;
}
