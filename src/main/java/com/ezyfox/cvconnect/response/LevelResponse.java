package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.LevelName;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LevelResponse {
    private long id;
    private LevelName levelName;
    private String meaning;
    private EntityStatus status;
}
