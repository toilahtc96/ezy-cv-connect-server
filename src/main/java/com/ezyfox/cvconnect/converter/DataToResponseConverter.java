package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.constant.RoleCode;
import com.ezyfox.cvconnect.constant.UserTypeCode;
import com.ezyfox.cvconnect.response.LoginResponse;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class DataToResponseConverter {
    public LoginResponse toLoginResponse(String accessToken, RoleCode roleCode, UserTypeCode userTypeCode) {
        return LoginResponse.builder().accessToken(accessToken).roleCode(roleCode).userTypeCode(userTypeCode).build();
    }
}
