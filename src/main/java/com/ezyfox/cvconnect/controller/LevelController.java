package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.LevelName;
import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.model.SearchLevelData;
import com.ezyfox.cvconnect.request.AddLevelRequest;
import com.ezyfox.cvconnect.request.EditLevelRequest;
import com.ezyfox.cvconnect.response.LevelResponse;
import com.ezyfox.cvconnect.service.LevelService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@Controller("api/v1/level")
@AllArgsConstructor
@Authenticated
public class LevelController {

    private final LevelService levelService;
    private final RequestToDataConverter requestToDataConverter;

    @DoPost("/add")
    public ResponseEntity addLevel(@RequestBody AddLevelRequest addLevelRequest) {
        levelService.addLevel(requestToDataConverter.toDataFromAddLevel(addLevelRequest));
        return ResponseEntity.noContent();
    }

    @DoPost("/edit")
    public ResponseEntity editLevel(@RequestBody EditLevelRequest editLevelRequest) {
        levelService.editLevel(requestToDataConverter.toDataFromEditLevel(editLevelRequest));
        return ResponseEntity.noContent();
    }

    @DoGet("/get-all")
    public List<LevelResponse> getAll() {
        return levelService.getAll();
    }

    @DoGet("/get-by-id")
    public LevelResponse getById(@RequestParam long levelId) {
        return levelService.getById(levelId);
    }

    @DoGet("/get-by-name")
    public LevelResponse getByName(@RequestParam LevelName levelName) {
        return levelService.getByLevelName(levelName);
    }

    @DoGet("/get-active")
    public List<LevelResponse> getLevelActive() {
        return levelService.getByStatus(EntityStatus.ACTIVED);
    }

    @DoGet("/get-page")
    public Map<String, Object> getPage(@RequestParam int page, @RequestParam int size) {
        int skip = size * page;
        SearchLevelData searchLevelData = SearchLevelData.builder().size(size).skip(skip).build();
        return levelService.getLevelPaging(searchLevelData);
    }
}
