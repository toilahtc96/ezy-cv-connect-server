package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.request.LoginRequest;
import com.ezyfox.cvconnect.request.RegisterRequest;
import com.ezyfox.cvconnect.response.LoginResponse;
import com.ezyfox.cvconnect.response.RegisterResponse;
import com.ezyfox.cvconnect.service.UserService;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;

@Controller("api/v1/authentication")
public class AuthenticationController {

    @EzyAutoBind
    private UserService userService;

    @DoPost("/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse registerResponse = new RegisterResponse();
        userService.registerUser(registerRequest);
        registerResponse.setMsg("Register success");
        return ResponseEntity.ok(registerResponse);
    }

    @DoPost("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        String accessToken = userService.login(loginRequest);
        loginResponse.setAccessToken(accessToken);
        return ResponseEntity.ok(loginResponse);
    }
}
