package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.response.LoginResponse;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class DataToResponseConverter {
    public LoginResponse toLoginResponse(String accessToken) {
        return LoginResponse.builder().accessToken(accessToken).build();
    }
}
