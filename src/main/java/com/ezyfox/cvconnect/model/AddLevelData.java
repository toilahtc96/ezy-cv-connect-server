package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.LevelName;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddLevelData {
    private LevelName levelName;
    private String meaning;
}
