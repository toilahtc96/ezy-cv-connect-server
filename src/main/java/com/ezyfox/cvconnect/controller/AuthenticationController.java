package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.model.LoginData;
import com.ezyfox.cvconnect.model.RegisterData;
import com.ezyfox.cvconnect.request.LoginRequest;
import com.ezyfox.cvconnect.request.RegisterRequest;
import com.ezyfox.cvconnect.response.LoginResponse;
import com.ezyfox.cvconnect.service.UserService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import lombok.AllArgsConstructor;

@Controller("api/v1/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @DoPost("/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
        RegisterData registerData = RegisterData
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
        userService.registerUser(registerData);
        return ResponseEntity.noContent();
    }

    @DoPost("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        LoginData loginData = LoginData
                .builder()
                .username(
                        loginRequest.getUsername()
                )
                .password(
                        loginRequest.getPassword()
                )
                .build();
        return ResponseEntity.ok(
                LoginResponse
                        .builder()
                        .accessToken(
                                userService.login(loginData)
                        )
                        .build());
    }
}
