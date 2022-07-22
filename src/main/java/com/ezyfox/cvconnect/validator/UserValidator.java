package com.ezyfox.cvconnect.validator;

import com.ezyfox.cvconnect.entity.User;
import com.ezyfox.cvconnect.request.LoginRequest;
import com.ezyfox.cvconnect.request.RegisterRequest;
import com.ezyfox.cvconnect.response.RegisterResponse;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.ezyhttp.core.exception.HttpNotFoundException;

import java.util.List;
import java.util.Objects;

@EzySingleton
public class UserValidator {
    public void validRegisterRequest(RegisterRequest registerRequest) {
        RegisterResponse response = new RegisterResponse();
        if (Objects.isNull(registerRequest)) {
            throw new HttpBadRequestException("Missing registration information");
        }
        if (Objects.isNull(registerRequest.getName())) {
            throw new HttpBadRequestException("Missing name information");
        }
        if (Objects.isNull(registerRequest.getUsername())) {
            throw new HttpBadRequestException("Missing username");
        }
        if (Objects.isNull(registerRequest.getPassword())) {
            throw new HttpBadRequestException("Missing password");
        }
        if (Objects.isNull(registerRequest.getTypeId())) {
            throw new HttpBadRequestException("Missing register type");
        }

    }

    public void validLoginRequest(LoginRequest loginRequest) {
        if (Objects.isNull(loginRequest)) {
            throw new HttpBadRequestException("Missing login information");
        }
        if (Objects.isNull(loginRequest.getUsername())) {
            throw new HttpBadRequestException("Missing username");
        }
        if (Objects.isNull(loginRequest.getPassword())) {
            throw new HttpBadRequestException("Missing password");
        }
    }

    public void validUsernameAndPassword(User userByUsername) {
        if (Objects.nonNull(userByUsername)) {
            throw new HttpBadRequestException("Username is already exist");
        }
    }

    public void validUserId(List<Long> userId) {
        if (Objects.isNull(userId) || userId.size() != 1) {
            throw new HttpNotFoundException("Login information is incorrect");
        }
    }
}
