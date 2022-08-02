package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.converter.DataToResponseConverter;
import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.request.LoginRequest;
import com.ezyfox.cvconnect.request.RegisterRequest;
import com.ezyfox.cvconnect.service.UserService;
import com.ezyfox.cvconnect.validator.UserValidator;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import lombok.AllArgsConstructor;

@Controller("api/v1/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final RequestToDataConverter requestToDataConverter;
    private final DataToResponseConverter dataToResponseConverter;
    private final UserValidator userValidator;

    @DoPost("/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
        userValidator.validateDateFromUserRegister(registerRequest);
        userService.registerUser(requestToDataConverter.toRegisterData(registerRequest));
        return ResponseEntity.noContent();
    }

    @DoPost("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(
            dataToResponseConverter.toLoginResponse(
                userService.login(
                    requestToDataConverter.toLoginData(loginRequest)
                )
            )
        );
    }
}
