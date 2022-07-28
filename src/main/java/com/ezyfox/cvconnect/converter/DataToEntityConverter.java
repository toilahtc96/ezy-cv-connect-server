package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.constant.AddressType;
import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.entity.Address;
import com.ezyfox.cvconnect.entity.User;
import com.ezyfox.cvconnect.model.AddAddressData;
import com.ezyfox.cvconnect.model.RegisterData;
import com.ezyfox.cvconnect.util.AddressUtil;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.sercurity.EzySHA256;
import lombok.AllArgsConstructor;

@EzySingleton
@AllArgsConstructor
public class DataToEntityConverter {

    private final AddressUtil addressUtil;

    public User toUserEntityFromData(RegisterData registerData) {
        return User.builder()
            .birthDay(registerData.getBirthDay())
            .name(registerData.getName())
            .username(registerData.getUsername())
            .password(EzySHA256.cryptUtfToLowercase(registerData.getPassword()))
            .typeId(registerData.getTypeId())
            .status(UserStatus.ACTIVE)
            .build();
    }

    public Address toAddressEntityFromAddData(AddAddressData addAddressData) {
        return Address
            .builder()
            .type(addAddressData.getType())
            .code(addressUtil
                .buildCodeOfAddress(
                    AddressType.of(addAddressData.getType()),
                    addAddressData.getParentId(),
                    addAddressData.getName()
                )
            )
            .name(addAddressData.getName())
            .parentId(addAddressData.getParentId())
            .build();
    }
}
