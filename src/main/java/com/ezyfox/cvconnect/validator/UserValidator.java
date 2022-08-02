package com.ezyfox.cvconnect.validator;

import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.entity.User;
import com.ezyfox.cvconnect.model.LoginData;
import com.ezyfox.cvconnect.model.RegisterData;
import com.ezyfox.cvconnect.repository.UserRepository;
import com.ezyfox.cvconnect.request.RegisterRequest;
import com.ezyfox.cvconnect.util.DateUtil;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.io.EzyStrings;
import com.tvd12.ezyfox.sercurity.EzySHA256;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.ezyhttp.core.exception.HttpNotFoundException;
import lombok.AllArgsConstructor;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EzySingleton
@AllArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

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

    public User validateUserLogin(LoginData logindata) {
        validLoginRequest(logindata);
        List<User> lstUserByUsername = userRepository.findByUsername(logindata.getUsername());
        validListUser(lstUserByUsername);
        User userLogin = lstUserByUsername
            .stream()
            .filter(user ->
                user.getStatus().equals(UserStatus.ACTIVE)
                    && user.getPassword().equals(
                    EzySHA256.cryptUtfToLowercase(logindata.getPassword())
                )
            ).findAny().orElse(null);
        validUser(userLogin);
        return userLogin;
    }

    public void validateUserRegister(RegisterData registerData) {
        validRegisterRequest(registerData);
        User userByUsername = userRepository.findByField("username", registerData.getUsername());
        validUsername(userByUsername);
    }
    public void validateDateFromUserRegister(RegisterRequest registerRequest) {
        if(registerRequest.getBirthDay() != null){
            try{
                DateUtil.parseFromStringFormat(registerRequest.getBirthDay(),DateUtil.DATE_DDMMYYYY_PATTERN);
            } catch (ParseException parseException) {
                throw new HttpNotFoundException("Date format is " + DateUtil.DATE_DDMMYYYY_PATTERN);
            }
        }
    }
}
