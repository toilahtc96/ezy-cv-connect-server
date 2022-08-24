package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.constant.UserTypeCode;
import com.ezyfox.cvconnect.entity.*;
import com.ezyfox.cvconnect.model.*;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.sercurity.EzySHA256;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@EzySingleton
@AllArgsConstructor
public class DataToEntityConverter {


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
            .status(EntityStatus.ACTIVE)
            .createdTime(LocalDateTime.now())
            .build();
    }

    public Role dataToRole(AddRoleData addRoleData) {
        return Role.builder()
            .name(addRoleData.getName())
            .code(addRoleData.getCode())
            .status(EntityStatus.ACTIVE)
            .createdTime(LocalDateTime.now())
            .build();
    }

    public UserType dataToUserType(AddUserTypeData addUserTypeData) {
        return UserType
            .builder()
            .code(UserTypeCode.of(addUserTypeData.getCode()))
            .meaning(addUserTypeData.getMeaning())
            .status(EntityStatus.ACTIVE)
            .createdTime(LocalDateTime.now())
            .build();
    }

    public UserType dataToUserType(EditUserTypeData editUserTypeData) {
        return UserType
            .builder()
            .id(editUserTypeData.getUserTypeId())
            .code(UserTypeCode.of(editUserTypeData.getCode()))
            .meaning(editUserTypeData.getMeaning())
            .status(editUserTypeData.getStatus())
            .build();
    }

    public User dataToAgencyUser(AddAgencyData addAgencyData) {
        return User
            .builder()
            .birthDay(addAgencyData.getBirthDay())
            .companyId(addAgencyData.getCompanyId())
            .status(addAgencyData.getStatus())
            .name(addAgencyData.getName())
            .information(addAgencyData.getInformation())
            .description(addAgencyData.getDescription())
            .username(addAgencyData.getUserName())
            .password(EzySHA256.cryptUtfToLowercase(addAgencyData.getPassword()))
            .roleId(addAgencyData.getRoleId())
            .typeId(addAgencyData.getTypeId())
            .star(addAgencyData.getStar())
            .createdTime(LocalDateTime.now())
            .build();
    }
}
