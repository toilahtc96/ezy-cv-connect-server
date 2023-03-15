package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.RoleCode;
import com.ezyfox.cvconnect.constant.UserTypeCode;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private String msg;
    private String accessToken;
    private RoleCode roleCode;
    private UserTypeCode userTypeCode;
}
