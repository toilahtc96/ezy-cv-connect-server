package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.ProcessCode;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EditProcessData {
    private long id;
    private ProcessCode code;
    private String meaning;
    private EntityStatus status;
}
