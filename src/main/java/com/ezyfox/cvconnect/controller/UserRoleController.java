package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.model.AddUserRoleData;
import com.ezyfox.cvconnect.model.EditUserRoleData;
import com.ezyfox.cvconnect.request.AddUserRoleRequest;
import com.ezyfox.cvconnect.request.EditUserRoleRequest;
import com.ezyfox.cvconnect.response.UserRoleResponse;
import com.ezyfox.cvconnect.service.UserRoleService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

@Controller("api/v1/user-role")
@AllArgsConstructor
@Authenticated
public class UserRoleController {

    private final UserRoleService userRoleService;
    private final RequestToDataConverter requestToDataConverter;

    @DoPost("/add")
    public ResponseEntity addUserRole(@RequestBody AddUserRoleRequest addUserRoleRequest) {
        AddUserRoleData addUserRoleData = requestToDataConverter
            .toDataFromAddUserRole(addUserRoleRequest);
        userRoleService.saveUserRole(addUserRoleData);
        return ResponseEntity.noContent();
    }

    @DoPost("/edit")
    public ResponseEntity editUserRole(@RequestBody EditUserRoleRequest editUserRoleRequest) {
        EditUserRoleData editUserRoleData = requestToDataConverter
            .toDataFromEditUserRole(editUserRoleRequest);
        userRoleService.editUserRole(editUserRoleData);
        return ResponseEntity.noContent();
    }

    @DoGet("/get-by-role-id")
    public List<UserRoleResponse> getUserRoleByRoleId(@RequestParam long roleId) {
        return userRoleService.getUserRoleByRoleId(roleId);
    }

    @DoGet("/get-by-user-id")
    public List<UserRoleResponse> getUserRoleByUserId(@RequestParam long userId) {
        return userRoleService.getUserRoleByUserId(userId);
    }

    @DoGet("/get-all")
    public List<UserRoleResponse> getAll() {
        return userRoleService.getAllUserRole();
    }

    @DoGet("/get-all-active")
    public List<UserRoleResponse> getAllActive() {
        return userRoleService.getAllUserRoleActive();
    }

    @DoGet("/get-by-id")
    public UserRoleResponse getById(@RequestParam long userRoleId) {
        return userRoleService.getById(userRoleId);
    }
}
