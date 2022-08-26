package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.LevelName;
import com.ezyfox.cvconnect.model.AddLevelData;
import com.ezyfox.cvconnect.model.EditLevelData;
import com.ezyfox.cvconnect.response.LevelResponse;

import java.util.List;

public interface LevelService {

    void addLevel(AddLevelData addLevelData);

    void editLevel(EditLevelData editLevelData);

    LevelResponse getById(long levelId);

    List<LevelResponse> getByLevelName(LevelName levelName);

    List<LevelResponse> getAll();

    List<LevelResponse> getByStatus(EntityStatus status);
}
