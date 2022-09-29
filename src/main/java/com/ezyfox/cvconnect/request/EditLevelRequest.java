package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.LevelName;
import lombok.Data;

@Data
public class EditLevelRequest {
    private long levelId;
    private LevelName name;
    private String meaning;
    private EntityStatus status;
}
