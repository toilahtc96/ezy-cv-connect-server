package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.model.LoginData;
import com.ezyfox.cvconnect.model.RegisterData;
import com.ezyfox.cvconnect.request.LoginRequest;
import com.ezyfox.cvconnect.request.RegisterRequest;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class RequestToDataConverter {

    public LoginData toLoginData(LoginRequest loginRequest) {
        return
            LoginData
                .builder()
                .username(
                    loginRequest.getUsername()
                )
                .password(
                    loginRequest.getPassword()
                )
                .build();
    }

    public RegisterData toRegisterData(RegisterRequest registerRequest) {
        return
            RegisterData
                .builder()
                .birthDay(registerRequest.getBirthDay())
                .name(registerRequest.getName())
                .cvLink(registerRequest.getCvLink())
                .levelId(registerRequest.getLevelId())
                .status(registerRequest.getStatus())
                .typeId(registerRequest.getTypeId())
                .password(registerRequest.getPassword())
                .yearExperience(registerRequest.getYearExperience())
                .username(registerRequest.getUsername())
                .build();
    }
}
