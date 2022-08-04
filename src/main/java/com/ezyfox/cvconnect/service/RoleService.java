package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.AddRoleData;
import com.ezyfox.cvconnect.model.RoleData;
import com.ezyfox.cvconnect.response.RoleResponse;

import java.util.List;

public interface RoleService {

    void addRole(AddRoleData roleData);

    void editRole(RoleData roleData);

    List<RoleResponse> getRoleByCodeActive(String code);

    List<RoleResponse> getRoleByNameActive(String name);

    List<RoleResponse> getAllRoleActive();

    List<RoleResponse> getAllRole();

}
