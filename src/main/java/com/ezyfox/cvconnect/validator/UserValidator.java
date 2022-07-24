package com.ezyfox.cvconnect.validator;

import com.ezyfox.cvconnect.entity.User;
import com.ezyfox.cvconnect.model.LoginData;
import com.ezyfox.cvconnect.model.RegisterData;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.io.EzyStrings;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.ezyhttp.core.exception.HttpNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@EzySingleton
public class UserValidator {

    public void validRegisterRequest(RegisterData registerData) {
        Map<String, String> errors = new HashMap<>();
        if (EzyStrings.isBlank(registerData.getName())) {
            errors.put("name", "required");
        }
        if (EzyStrings.isBlank(registerData.getUsername())) {
            errors.put("username", "required");
        }
        if (EzyStrings.isBlank(registerData.getPassword())) {
            errors.put("password", "required");
        }
        if (registerData.getTypeId() == null) {
            errors.put("type_id", "required");
        }
        if (errors.size() > 0) {
            throw new HttpBadRequestException(errors);
        }
    }

    public void validLoginRequest(LoginData logindata) {
        Map<String, String> errors = new HashMap<>();
        if (EzyStrings.isBlank(logindata.getUsername())) {
            errors.put("username", "required");
        }
        if (EzyStrings.isBlank(logindata.getPassword())) {
            errors.put("password", "required");
        }
        if (errors.size() > 0) {
            throw new HttpBadRequestException(errors);
        }
    }

    public void validUsername(User userByUsername) {
        Map<String, String> errors = new HashMap<>();
        if (userByUsername != null) {
            errors.put("Username", "already existed");
        }
        if (errors.size() > 0) {
            throw new HttpBadRequestException(errors);
        }
    }

    public void validListUser(List<User> lstUser) {
        Map<String, String> errors = new HashMap<>();
        if (lstUser == null || lstUser.size() != 1) {
            errors.put("Login", "has error");
        }
        if (errors.size() > 0) {
            throw new HttpBadRequestException(errors);
        }
    }

    public void validUser(User user) {
        if (user == null) {
            throw new HttpNotFoundException("user not found");
        }
    }
}
