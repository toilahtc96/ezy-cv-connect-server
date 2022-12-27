package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.UserRole;
import com.ezyfox.cvconnect.exception.ResourceNotFoundException;
import com.ezyfox.cvconnect.model.AddUserRoleData;
import com.ezyfox.cvconnect.model.EditUserRoleData;
import com.ezyfox.cvconnect.repository.UserRoleRepository;
import com.ezyfox.cvconnect.response.UserRoleResponse;
import com.ezyfox.cvconnect.service.UserRoleService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final DataToEntityConverter dataToEntityConverter;
    private final EntityToResponseConverter entityToResponseConverter;

    @Override
    public void saveUserRole(AddUserRoleData data) {
        UserRole userRole = dataToEntityConverter.dataToUserRole(data);
        userRole.setCreatedTime(LocalDateTime.now());
        userRoleRepository.save(userRole);
    }

    @Override
    public void editUserRole(EditUserRoleData data) {
        UserRole userRoleById = userRoleRepository.findById(data.getUserRoleId());
        if (userRoleById == null) {
            throw new ResourceNotFoundException("UserRole");
        }
        if (!userRoleById.getStatus().equals(EntityStatus.ACTIVED)) {
            throw new ResourceNotFoundException("UserRole Active by id");
        }
        userRoleById.setRoleId(data.getRoleId());
        userRoleById.setUserId(data.getUserId());
        userRoleById.setUpdatedTime(LocalDateTime.now());
        userRoleById.setStatus(data.getStatus());
        userRoleRepository.save(userRoleById);
    }

    @Override
    public List<UserRoleResponse> getUserRoleByRoleId(long roleId) {
        return userRoleRepository
            .findByRoleId(roleId)
            .stream()
            .map(entityToResponseConverter::toUserRoleResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<UserRoleResponse> getUserRoleByUserId(long userId) {
        return userRoleRepository
            .findByUserId(userId)
            .stream()
            .map(entityToResponseConverter::toUserRoleResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<UserRoleResponse> getAllUserRole() {
        return userRoleRepository
            .findAll()
            .stream()
            .map(entityToResponseConverter::toUserRoleResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<UserRoleResponse> getAllUserRoleActive() {
        return userRoleRepository
            .findAllActive(EntityStatus.ACTIVED)
            .stream()
            .map(entityToResponseConverter::toUserRoleResponse)
            .collect(Collectors.toList());
    }

    @Override
    public UserRoleResponse getById(long userRoleId) {
        UserRole userRole = userRoleRepository.findById(userRoleId);
        return entityToResponseConverter.toUserRoleResponse(userRole);
    }
}
