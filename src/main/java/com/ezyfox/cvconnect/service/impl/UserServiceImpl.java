package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.entity.User;
import com.ezyfox.cvconnect.model.LoginData;
import com.ezyfox.cvconnect.model.RegisterData;
import com.ezyfox.cvconnect.repository.UserRepository;
import com.ezyfox.cvconnect.service.AuthenticationService;
import com.ezyfox.cvconnect.service.UserService;
import com.ezyfox.cvconnect.validator.UserValidator;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.sercurity.EzySHA256;
import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@EzySingleton
@AllArgsConstructor
public class UserServiceImpl extends EzyLoggable implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final UserValidator userValidator;
    private final DataToEntityConverter dataToEntityConverter;

    @Override
    public void registerUser(RegisterData registerData) {
        userValidator.validRegisterRequest(registerData);
        User userByUsername = userRepository.findByField("username", registerData.getUsername());
        userValidator.validUsernameAndPassword(userByUsername);
        User newUser = dataToEntityConverter.toUserEntityFromData(registerData);
        newUser.setCreatedTime(LocalDateTime.now());
        userRepository.save(newUser);
    }

    @Override
    public String login(LoginData logindata) {
        userValidator.validLoginRequest(logindata);
        List<Long> userId = userRepository.findByUsernameAndPasswordAndStatus(
            logindata.getUsername(),
            EzySHA256.cryptUtfToLowercase(logindata.getPassword()),
            UserStatus.ACTIVE
        );
        userValidator.validUserId(userId);
        String accessToken = "";
        try {
            accessToken = authenticationService.generateAccessToken(userId.get(0));
        } catch (Exception ex) {
            throw new HttpBadRequestException("Generate Token has error");
        }
        if (accessToken.isEmpty()) {
            throw new HttpBadRequestException("Generate Token has error");
        }
        return accessToken;
    }
}
