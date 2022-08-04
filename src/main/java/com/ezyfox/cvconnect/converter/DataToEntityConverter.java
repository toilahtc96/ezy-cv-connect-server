package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.entity.*;
import com.ezyfox.cvconnect.model.*;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.sercurity.EzySHA256;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@EzySingleton
@AllArgsConstructor
public class DataToEntityConverter {

    private final int ACTIVE = 1;
    private final int BLOCK = 0;
    public User dataToUser(RegisterData registerData) {
        User user =  User.builder()
            .birthDay(registerData.getBirthDay())
            .name(registerData.getName())
            .username(registerData.getUsername())
            .password(EzySHA256.cryptUtfToLowercase(registerData.getPassword()))
            .typeId(registerData.getTypeId())
            .status(UserStatus.ACTIVE)
            .createdTime(LocalDateTime.now())
            .build();
        return user;
    }

    public Address dataToAddress(AddAddressData addAddressData) {
        return Address.builder()
            .type(addAddressData.getType())
            .name(addAddressData.getName())
            .parentId(addAddressData.getParentId())
            .status(ACTIVE)
            .createdTime(LocalDateTime.now())
            .build();
    }

    public Role dataToRole(AddRoleData addRoleData) {
        return Role.builder()
            .name(addRoleData.getName())
            .code(addRoleData.getCode())
            .status(ACTIVE)
            .createdTime(LocalDateTime.now())
            .build();
    }

    public UserType dataToUserType(AddUserTypeData addUserTypeData) {
        return UserType
            .builder()
            .code(addUserTypeData.getCode())
            .meaning(addUserTypeData.getMeaning())
            .status(ACTIVE)
            .createdTime(LocalDateTime.now())
            .build();
    }

    public UserType dataToUserType(EditUserTypeData editUserTypeData) {
        return UserType
            .builder()
            .id(editUserTypeData.getId())
            .code(editUserTypeData.getCode())
            .meaning(editUserTypeData.getMeaning())
            .status(editUserTypeData.getStatus())
            .build();
    }
}
