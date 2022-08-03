package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.Role;
import com.ezyfox.cvconnect.model.AddRoleData;
import com.ezyfox.cvconnect.model.RoleData;
import com.ezyfox.cvconnect.repository.RoleRepository;
import com.ezyfox.cvconnect.response.RoleResponse;
import com.ezyfox.cvconnect.service.RoleService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@EzySingleton
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final DataToEntityConverter dataToEntityConverter;
    private final EntityToResponseConverter entityToResponseConverter;
    private final int ACTIVE = 1;


    @Override
    public void addRole(AddRoleData roleData) {
        Role role = dataToEntityConverter.dataToRole(roleData);
        role.setCreatedTime(LocalDateTime.now());
        roleRepository.save(role);
    }

    @Override
    public void editRole(RoleData roleData) {
        Role roleById = roleRepository.findById(roleData.getId());
        if(roleById == null) {
            throw new HttpBadRequestException("Role By Id Not Found");
        }
        if (roleById.getStatus() != 1) {
            throw new HttpBadRequestException("Role By Id Not Active");
        }
        if (roleData.getName() != null) {
            roleById.setName(roleData.getName());
        }
        if (roleData.getCode() != null) {
            roleById.setCode(roleData.getCode());
        }
        roleById.setStatus(roleData.getStatus());
        roleById.setUpdatedTime(LocalDateTime.now());
        roleRepository.save(roleById);
    }

    @Override
    public List<RoleResponse> getRoleByCodeActive(String code) {
        return entityToResponseConverter.toListRoleResponse(
            roleRepository.getRoleByCodeAndStatus(code, ACTIVE)
        );
    }

    @Override
    public List<RoleResponse> getRoleByNameActive(String name) {
        return entityToResponseConverter.toListRoleResponse(
            roleRepository.getRoleByNameAndStatus(name, ACTIVE)
        );
    }

    @Override
    public List<RoleResponse> getAllRoleActive() {
        return entityToResponseConverter.toListRoleResponse(
            roleRepository.getAllRoleByStatus(ACTIVE)
        );
    }

    @Override
    public List<RoleResponse> getAllRole() {
        return entityToResponseConverter.toListRoleResponse(roleRepository.getAllRole());
    }
}
