package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.request.LoginRequest;
import com.ezyfox.cvconnect.request.RegisterRequest;
import com.ezyfox.cvconnect.response.ResponseEntityData;
import com.ezyfox.cvconnect.services.IUserService;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;

@Controller("api/v1/authentication")
public class AuthenticationController {

    @EzyAutoBind
    private IUserService userService;

    @DoPost("/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
        ResponseEntityData responseEntityData = userService.registerUser(registerRequest);
        if (!responseEntityData.isStatus()) {
            return ResponseEntity.badRequest(responseEntityData);
        }
        return ResponseEntity.ok(responseEntityData);
    }

    @DoPost("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        ResponseEntityData responseEntityData = userService.login(loginRequest);
        if (!responseEntityData.isStatus()) {
            return ResponseEntity.badRequest(responseEntityData);
        }
        return ResponseEntity.ok(responseEntityData);
    }
}
