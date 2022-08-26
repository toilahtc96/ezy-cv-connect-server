package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.LevelName;
import lombok.Data;

@Data
public class AddLevelRequest {
    private LevelName name;
    private String meaning;
}
