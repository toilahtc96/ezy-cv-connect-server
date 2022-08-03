package com.ezyfox.cvconnect.controller;

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
        AddRoleData addRoleData = requestToDataConverter.toData(addRoleRequest);
        roleService.addRole(addRoleData);
        return ResponseEntity.noContent();
    }

    @DoPost("/edit")
    public ResponseEntity editRole(@RequestBody RoleRequest editRoleRequest) {
        RoleData roleData = requestToDataConverter.toData(editRoleRequest);
        roleService.editRole(roleData);
        return ResponseEntity.noContent();
    }

    @DoGet("/get-by-code")
    public ResponseEntity getByCode(@RequestParam String code) {
        List<RoleResponse> listByCode = roleService.getRoleByCodeActive(code);
        return ResponseEntity.ok(listByCode);
    }

    @DoGet("/get-by-name")
    public ResponseEntity getByName(@RequestParam String name) {
        List<RoleResponse> listByName = roleService.getRoleByNameActive(name);
        return ResponseEntity.ok(listByName);
    }

    @DoGet("/get-all-active")
    public ResponseEntity getAllActive() {
        List<RoleResponse> listActive = roleService.getAllRoleActive();
        return ResponseEntity.ok(listActive);
    }

    @DoGet("/get-all")
    public ResponseEntity getAll() {
        List<RoleResponse> listAll = roleService.getAllRole();
        return ResponseEntity.ok(listAll);
    }
}
