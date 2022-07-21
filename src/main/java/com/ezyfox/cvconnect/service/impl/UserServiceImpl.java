package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.entity.User;
import com.ezyfox.cvconnect.repository.UserRepository;
import com.ezyfox.cvconnect.request.LoginRequest;
import com.ezyfox.cvconnect.request.RegisterRequest;
import com.ezyfox.cvconnect.service.AuthenticationService;
import com.ezyfox.cvconnect.service.UserService;
import com.ezyfox.cvconnect.validator.UserValidator;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.sercurity.EzySHA256;
import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.ezyhttp.server.core.annotation.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl extends EzyLoggable implements UserService {

    @EzyAutoBind
    private UserRepository userRepository;
    @EzyAutoBind
    private AuthenticationService authenticationService;
    @EzyAutoBind
    private UserValidator userValidator;


    @Override
    public void registerUser(RegisterRequest registerRequest) {
        userValidator.validRegisterRequest(registerRequest);

        User userByUsername = userRepository.findByField("username", registerRequest.getUsername());
        userValidator.validUsernameAndPassword(userByUsername);
        User newUser = new User().of(registerRequest);
        Long newId = userRepository.getMaxUserId();
        if (Objects.isNull(newId)) {
            newId = 1L;
        }
        newUser.setId(newId);
        try {
            newUser.setCreatedTime(LocalDateTime.now());
            userRepository.save(newUser);
        } catch (Exception ex) {
            throw new HttpBadRequestException("Có lỗi khi lưu thông tin đăng kí");
        }
    }


    @Override
    public String login(LoginRequest loginRequest) {
        userValidator.validLoginRequest(loginRequest);
        List<Long> userId = userRepository.findByUsernameAndPasswordAndStatus(
                loginRequest.getUsername(),
                EzySHA256.cryptUtfToLowercase(loginRequest.getPassword()),
                UserStatus.ACTIVE.getStatus()
        );
        userValidator.validUserId(userId);
        String accessToken = "";
        try {
            accessToken = authenticationService.generateAccessToken(userId.get(0));
        } catch (Exception ex) {
            throw new HttpBadRequestException("Tạo token lỗi");
        }
        if (accessToken.isEmpty()) {
            throw new HttpBadRequestException("Tạo token lỗi");
        }
        return accessToken;
    }
}
