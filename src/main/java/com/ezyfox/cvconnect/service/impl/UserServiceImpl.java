package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.entity.User;
import com.ezyfox.cvconnect.model.LoginData;
import com.ezyfox.cvconnect.model.RegisterData;
import com.ezyfox.cvconnect.repository.UserRepository;
import com.ezyfox.cvconnect.service.AuthenticationService;
import com.ezyfox.cvconnect.service.UserService;
import com.ezyfox.cvconnect.validator.UserValidator;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.util.EzyLoggable;
import lombok.AllArgsConstructor;

@EzySingleton
@AllArgsConstructor
public class UserServiceImpl extends EzyLoggable implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final UserValidator userValidator;
    private final DataToEntityConverter dataToEntityConverter;

    @Override
    public void registerUser(RegisterData registerData) {
        userValidator.validateUserRegister(registerData);
        User newUser = dataToEntityConverter.dataToUser(registerData);
        newUser.setStatus(null);
        userRepository.save(newUser);
    }

    @Override
    public String login(LoginData logindata) {
        User userLogin = userValidator.validateUserLogin(logindata);
        return authenticationService.generateAccessToken(userLogin.getId());
    }


}
