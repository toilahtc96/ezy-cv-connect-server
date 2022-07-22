package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.entity.User;
import com.ezyfox.cvconnect.model.RegisterData;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.sercurity.EzySHA256;

@EzySingleton
public class DataToEntityConverter {

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
}
