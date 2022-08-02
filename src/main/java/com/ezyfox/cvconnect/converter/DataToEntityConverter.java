package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.entity.Address;
import com.ezyfox.cvconnect.entity.Role;
import com.ezyfox.cvconnect.entity.User;
import com.ezyfox.cvconnect.model.AddAddressData;
import com.ezyfox.cvconnect.model.AddRoleData;
import com.ezyfox.cvconnect.model.RegisterData;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.sercurity.EzySHA256;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@EzySingleton
@AllArgsConstructor
public class DataToEntityConverter {

    private final int ACTIVE = 1;
    private final int BLOCK = 0;
    public User toUserEntityFromData(RegisterData registerData) {
        User user =  User.builder()
            .birthDay(registerData.getBirthDay())
            .name(registerData.getName())
            .username(registerData.getUsername())
            .password(EzySHA256.cryptUtfToLowercase(registerData.getPassword()))
            .typeId(registerData.getTypeId())
            .status(UserStatus.ACTIVE)
            .build();
        user.setCreatedTime(LocalDateTime.now());
        return user;
    }

    public Address toAddressEntityFromAddData(AddAddressData addAddressData) {
        return Address.builder()
            .type(addAddressData.getType())
            .name(addAddressData.getName())
            .parentId(addAddressData.getParentId())
            .status(ACTIVE)
            .build();
    }

    public Role toRoleEntityFromAddData(AddRoleData addRoleData) {
        return Role.builder()
            .name(addRoleData.getName())
            .code(addRoleData.getCode())
            .status(ACTIVE)
            .build();
    }
}
