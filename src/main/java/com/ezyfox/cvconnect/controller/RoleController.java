package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.constant.RoleCode;
import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.model.AddRoleData;
import com.ezyfox.cvconnect.model.RoleData;
import com.ezyfox.cvconnect.request.AddRoleRequest;
import com.ezyfox.cvconnect.request.RoleRequest;
import com.ezyfox.cvconnect.response.RoleResponse;
import com.ezyfox.cvconnect.service.RoleService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

@Controller("api/v1/role")
@AllArgsConstructor
@Authenticated
public class RoleController {

    private final RoleService roleService;
    private final RequestToDataConverter requestToDataConverter;

    @DoPost("/add")
    public ResponseEntity addRole(@RequestBody AddRoleRequest addRoleRequest) {
        AddRoleData addRoleData = requestToDataConverter.toDataFromAddRole(addRoleRequest);
        roleService.addRole(addRoleData);
        return ResponseEntity.noContent();
    }

    @DoPost("/edit")
    public ResponseEntity editRole(@RequestBody RoleRequest editRoleRequest) {
        RoleData roleData = requestToDataConverter.toDataFromEditRole(editRoleRequest);
        roleService.editRole(roleData);
        return ResponseEntity.noContent();
    }

    @DoGet("/get-by-id")
    public RoleResponse getById(@RequestParam long id) {
        return roleService.getById(id);
    }

    @DoGet("/get-by-code")
    public List<RoleResponse> getByCode(@RequestParam RoleCode code) {
        return roleService.getRoleByCodeActive(code);
    }

    @DoGet("/get-by-name")
    public List<RoleResponse> getByName(@RequestParam String name) {
        return roleService.getRoleByNameActive(name);
    }

    @DoGet("/get-all-active")
    public List<RoleResponse> getAllActive() {
        return roleService.getAllRoleActive();
    }

    @DoGet("/get-all")
    public List<RoleResponse> getAll() {
        return roleService.getAllRole();
    }

    @DoGet("/get-page")
    public List<RoleResponse> getPage(@RequestParam int page, @RequestParam int size) {
        return roleService.getPaging(page, size);
    }
}
