package com.ezyfox.cvconnect.validator;

import com.ezyfox.cvconnect.entity.User;
import com.ezyfox.cvconnect.model.LoginData;
import com.ezyfox.cvconnect.model.RegisterData;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.io.EzyStrings;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;

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
        if (Objects.isNull(registerData.getTypeId())) {
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

    public void validUsernameAndPassword(User userByUsername) {
        Map<String, String> errors = new HashMap<>();
        if (Objects.nonNull(userByUsername)) {
            errors.put("Username", "already existed");
        }
        if (errors.size() > 0) {
            throw new HttpBadRequestException(errors);
        }
    }

    public void validUserId(List<Long> userId) {
        Map<String, String> errors = new HashMap<>();
        if (Objects.isNull(userId) || userId.size() != 1) {
            errors.put("Login", "has error");
        }
        if (errors.size() > 0) {
            throw new HttpBadRequestException(errors);
        }
    }
}
