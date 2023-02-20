package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.LevelName;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Builder
public class SearchLevelData {
    private String meaning;
    @Enumerated(EnumType.STRING)
    private LevelName name;
    private EntityStatus status;
    private int size;
    private int skip;
}
