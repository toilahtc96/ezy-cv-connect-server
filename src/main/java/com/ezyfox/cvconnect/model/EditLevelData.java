package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.LevelName;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EditLevelData {
    private long levelId;
    private LevelName levelName;
    private String meaning;
    private EntityStatus status;
}
