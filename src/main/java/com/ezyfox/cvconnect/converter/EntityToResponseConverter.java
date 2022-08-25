package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.entity.Address;
import com.ezyfox.cvconnect.entity.Process;
import com.ezyfox.cvconnect.entity.Role;
import com.ezyfox.cvconnect.entity.UserRole;
import com.ezyfox.cvconnect.entity.UserType;
import com.ezyfox.cvconnect.response.*;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

import java.util.List;
import java.util.stream.Collectors;

@EzySingleton
public class EntityToResponseConverter {

    public AddressResponse toResponse(Address address) {
        return AddressResponse
            .builder()
            .type(address.getType())
            .parentId(address.getParentId())
            .name(address.getName())
            .id(address.getId())
            .code(address.getCode())
            .name(address.getName())
            .build();
    }

    public RoleResponse toResponse(Role role) {
        return RoleResponse
            .builder()
            .name(role.getName())
            .id(role.getId())
            .code(role.getCode())
            .status(role.getStatus())
            .build();
    }

    public List<RoleResponse> toListRoleResponse(List<Role> roles) {
        return roles.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public UserTypeResponse toUserTypeResponse(UserType userType) {
        return UserTypeResponse
            .builder()
            .id(userType.getId())
            .code(userType.getCode().getName())
            .meaning(userType.getMeaning())
            .build();
    }

    public List<UserTypeResponse> toListUserTypeResponse(List<UserType> userTypes) {
        return userTypes.stream().map(this::toUserTypeResponse).collect(Collectors.toList());
    }

    public UserRoleResponse toUserRoleResponse(UserRole userRole) {
        return UserRoleResponse
            .builder()
            .userRoleId(userRole.getId())
            .roleId(userRole.getRoleId())
            .userId(userRole.getUserId())
            .status(userRole.getStatus())
            .build();
    }

    public ProcessResponse toProcessResponse(Process process) {
        return ProcessResponse
            .builder()
            .processId(process.getId())
            .code(process.getCode())
            .meaning(process.getMeaning())
            .status(process.getStatus())
            .build();
    }
}
