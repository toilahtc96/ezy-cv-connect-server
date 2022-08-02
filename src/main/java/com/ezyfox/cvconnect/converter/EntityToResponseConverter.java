package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.entity.Address;
import com.ezyfox.cvconnect.entity.Role;
import com.ezyfox.cvconnect.response.AddressResponse;
import com.ezyfox.cvconnect.response.RoleResponse;
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

    public static RoleResponse toResponse(Role role) {
        return RoleResponse
            .builder()
            .name(role.getName())
            .id(role.getId())
            .code(role.getCode())
            .status(role.getStatus())
            .build();
    }

    public List<RoleResponse> toListResponse(List<Role> roles) {
        return roles.stream().map(EntityToResponseConverter::toResponse).collect(Collectors.toList());
    }
}
