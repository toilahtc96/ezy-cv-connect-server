package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.constant.UserTypeCode;
import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.model.AddUserTypeData;
import com.ezyfox.cvconnect.model.EditUserTypeData;
import com.ezyfox.cvconnect.request.AddUserTypeRequest;
import com.ezyfox.cvconnect.request.EditUserTypeRequest;
import com.ezyfox.cvconnect.response.UserTypeResponse;
import com.ezyfox.cvconnect.service.UserTypeService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

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
    public ResponseEntity getAll() {
        List<UserTypeResponse> listAll = userTypeService.getAllUserType();
        return ResponseEntity.ok(listAll);
    }

    @DoGet("/get-by-id")
    public ResponseEntity getById(@RequestParam long id) {
        return ResponseEntity.ok(userTypeService.getById(id));
    }
}
