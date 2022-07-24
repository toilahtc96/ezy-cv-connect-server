package com.ezyfox.cvconnect.response;

import lombok.*;

@Getter
@Builder
public class LoginResponse {
    private String msg;
    private String accessToken;
}
