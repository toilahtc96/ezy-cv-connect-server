package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.constant.UserTypeCode;
import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.model.AddUserTypeData;
import com.ezyfox.cvconnect.model.EditUserTypeData;
import com.ezyfox.cvconnect.model.SearchUserTypeData;
import com.ezyfox.cvconnect.request.AddUserTypeRequest;
import com.ezyfox.cvconnect.request.EditUserTypeRequest;
import com.ezyfox.cvconnect.response.UserTypeResponse;
import com.ezyfox.cvconnect.service.UserTypeService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@Controller("api/v1/user-type")
@AllArgsConstructor
@Authenticated
public class UserTypeController {

    private final UserTypeService userTypeService;
    private final RequestToDataConverter requestToDataConverter;

    @DoPost("/add")
    public ResponseEntity addUserType(@RequestBody AddUserTypeRequest addUserTypeRequest) {
        AddUserTypeData addUserTypeData = requestToDataConverter.toDataFromAddUserType(addUserTypeRequest);
        userTypeService.saveUserType(addUserTypeData);
        return ResponseEntity.noContent();
    }

    @DoPost("/edit")
    public ResponseEntity editUserType(@RequestBody EditUserTypeRequest editUserTypeRequest) {
        EditUserTypeData userTypeData = requestToDataConverter.toDataFromEditUserType(editUserTypeRequest);
        userTypeService.editUserType(userTypeData);
        return ResponseEntity.noContent();
    }

    @DoGet("/get-by-code")
    public ResponseEntity getByCode(@RequestParam String code) {
        UserTypeResponse listUserType = userTypeService.getUserTypeByCode(UserTypeCode.of(code));
        return ResponseEntity.ok(listUserType);
    }

    @DoGet("/get-all-active")
    public ResponseEntity getAllActive() {
        List<UserTypeResponse> listActive = userTypeService.getAllUserTypeActive();
        return ResponseEntity.ok(listActive);
    }

    @DoGet("/get-all")
    public List<UserTypeResponse> getAll() {
        return userTypeService.getAllUserType();
    }

    @DoGet("/get-by-id")
    public UserTypeResponse getById(@RequestParam long id) {
        return userTypeService.getById(id);
    }

    @DoGet("/get-page")
    public Map<String, Object> getPage(@RequestParam int page, @RequestParam int size) {
        int skip = size * page;
        SearchUserTypeData searchUserTypeData = SearchUserTypeData.builder().size(size).skip(skip).build();
        return userTypeService.getUserTypePaging(searchUserTypeData);
    }
}
