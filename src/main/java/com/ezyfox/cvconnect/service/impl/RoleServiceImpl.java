package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.RoleCode;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.Role;
import com.ezyfox.cvconnect.exception.ResourceNotFoundException;
import com.ezyfox.cvconnect.model.AddRoleData;
import com.ezyfox.cvconnect.model.RoleData;
import com.ezyfox.cvconnect.repository.RoleRepository;
import com.ezyfox.cvconnect.response.RoleResponse;
import com.ezyfox.cvconnect.service.RoleService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final DataToEntityConverter dataToEntityConverter;
    private final EntityToResponseConverter entityToResponseConverter;


    @Override
    public void addRole(AddRoleData roleData) {
        Role role = dataToEntityConverter.dataToRole(roleData);
        roleRepository.save(role);
    }

    @Override
    public void editRole(RoleData roleData) {
        Role roleById = roleRepository.findById(roleData.getId());
        if (roleById == null) {
            throw new ResourceNotFoundException("Role By Id");
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
    public List<RoleResponse> getRoleByCodeActive(RoleCode code) {
        return entityToResponseConverter.toListRoleResponse(
            roleRepository.getRoleByCodeAndStatus(code, EntityStatus.ACTIVED)
        );
    }

    @Override
    public List<RoleResponse> getRoleByNameActive(String name) {
        return entityToResponseConverter.toListRoleResponse(
            roleRepository.getRoleByNameAndStatus(name, EntityStatus.ACTIVED)
        );
    }

    @Override
    public List<RoleResponse> getAllRoleActive() {
        return entityToResponseConverter.toListRoleResponse(
            roleRepository.getAllRoleByStatus(EntityStatus.ACTIVED)
        );
    }

    @Override
    public List<RoleResponse> getAllRole() {
        return entityToResponseConverter.toListRoleResponse(roleRepository.getAllRole());
    }

    @Override
    public List<RoleResponse> getPaging(int page, int size) {
        int skip = page * size;
        return roleRepository
                .findAll(skip, size)
                .stream()
                .map(entityToResponseConverter::toRoleResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponse getById(long id) {
        return entityToResponseConverter.toRoleResponse(roleRepository.findById(id));
    }
}
