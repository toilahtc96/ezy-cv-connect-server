package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.LevelName;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.Level;
import com.ezyfox.cvconnect.exception.ResourceNotFoundException;
import com.ezyfox.cvconnect.model.AddLevelData;
import com.ezyfox.cvconnect.model.EditLevelData;
import com.ezyfox.cvconnect.repository.LevelRepository;
import com.ezyfox.cvconnect.response.LevelResponse;
import com.ezyfox.cvconnect.service.LevelService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;
import org.eclipse.jetty.util.StringUtil;

import java.util.List;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;
    private final EntityToResponseConverter entityToResponseConverter;
    private final DataToEntityConverter dataToEntityConverter;

    @Override
    public void addLevel(AddLevelData addLevelData) {
        levelRepository.save(dataToEntityConverter.dataToLevel(addLevelData));
    }

    @Override
    public void editLevel(EditLevelData editLevelData) {
        Level levelById = levelRepository.findById(editLevelData.getLevelId());
        if (levelById == null) {
            throw new ResourceNotFoundException("Level");
        }
        if (!StringUtil.isBlank(editLevelData.getMeaning())) {
            levelById.setMeaning(editLevelData.getMeaning());
        }
        if (editLevelData.getLevelName() != null) {
            levelById.setName(editLevelData.getLevelName());
        }
        if (editLevelData.getStatus() != null) {
            levelById.setStatus(editLevelData.getStatus());
        }
        levelRepository.save(levelById);
    }

    @Override
    public LevelResponse getById(long levelId) {
        return entityToResponseConverter.toLevelResponse(levelRepository.findById(levelId));
    }

    @Override
    public List<LevelResponse> getByLevelName(LevelName levelName) {
        return levelRepository
            .getLevelByName(levelName)
            .stream()
            .map(entityToResponseConverter::toLevelResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<LevelResponse> getAll() {
        return levelRepository
            .findAll()
            .stream()
            .map(entityToResponseConverter::toLevelResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<LevelResponse> getByStatus(EntityStatus status) {
        return levelRepository
            .getLevelByStatus(status)
            .stream()
            .map(entityToResponseConverter::toLevelResponse)
            .collect(Collectors.toList());
    }
}
