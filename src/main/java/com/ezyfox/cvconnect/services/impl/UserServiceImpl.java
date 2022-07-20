package com.ezyfox.cvconnect.services.impl;

import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.entity.User;
import com.ezyfox.cvconnect.repository.UserRepository;
import com.ezyfox.cvconnect.request.LoginRequest;
import com.ezyfox.cvconnect.request.RegisterRequest;
import com.ezyfox.cvconnect.response.ResponseEntityData;
import com.ezyfox.cvconnect.services.IAuthenticationService;
import com.ezyfox.cvconnect.services.IUserService;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.sercurity.EzySHA256;
import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.server.core.annotation.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl extends EzyLoggable implements IUserService {

    @EzyAutoBind
    private UserRepository userRepository;
    @EzyAutoBind
    private IAuthenticationService authenticationService;

    @Override
    public ResponseEntityData isValidRegisterRequest(RegisterRequest registerRequest) {
        ResponseEntityData response = new ResponseEntityData();
        if (Objects.isNull(registerRequest)) {
            logger.error("Thiếu thông tin đăng kí");
            response.setMsg("Thiếu thông tin đăng kí");
            response.setStatus(false);
            return response;
        }
        if (Objects.isNull(registerRequest.getName())) {
            logger.error("Thiếu thông tin tên người sử dụng");
            response.setMsg("Thiếu thông tin tên người sử dụng");
            response.setStatus(false);
            return response;
        }
        if (Objects.isNull(registerRequest.getUsername())) {
            logger.error("Thiếu thông tin tên đăng nhập");
            response.setMsg("Thiếu thông tin tên đăng nhập");
            response.setStatus(false);
            return response;
        }
        if (Objects.isNull(registerRequest.getPassword())) {
            logger.error("Thiếu thông tin mật khẩu");
            response.setMsg("Thiếu thông tin mật khẩu");
            response.setStatus(false);
            return response;
        }
        if (Objects.isNull(registerRequest.getTypeId())) {
            logger.error("Thiếu thông tin loại hình đăng kí");
            response.setMsg("Thiếu thông tin loại hình đăng kí");
            response.setStatus(false);
            return response;
        }
        response.setMsg("Thành Công");
        response.setStatus(true);
        return response;

    }

    @Override
    public ResponseEntityData registerUser(RegisterRequest registerRequest) {
        ResponseEntityData responseEntityData = isValidRegisterRequest(registerRequest);
        if (!responseEntityData.isStatus()) {
            return responseEntityData;
        }
        User userByUsername = userRepository.findByField("username", registerRequest.getUsername());
        if (Objects.nonNull(userByUsername)) {
            responseEntityData.setMsg("Tên người dùng đã tồn tại");
            responseEntityData.setStatus(false);
            return responseEntityData;
        }
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
            responseEntityData.setStatus(false);
            responseEntityData.setMsg("có lỗi khi lưu thông tin " + ex.getMessage());
        }
        return responseEntityData;
    }

    @Override
    public ResponseEntityData isValidLoginRequest(LoginRequest loginRequest) {
        ResponseEntityData response = new ResponseEntityData();
        if (Objects.isNull(loginRequest)) {
            logger.error("Thiếu thông tin đăng nhập");
            response.setMsg("Thiếu thông tin đăng nhập");
            response.setStatus(false);
            return response;
        }
        if (Objects.isNull(loginRequest.getUsername())) {
            logger.error("Thiếu thông tin tên đăng nhập");
            response.setMsg("Thiếu thông tin tên đăng nhập");
            response.setStatus(false);
            return response;
        }
        if (Objects.isNull(loginRequest.getPassword())) {
            logger.error("Thiếu thông tin mật khẩu");
            response.setMsg("Thiếu thông tin mật khẩu");
            response.setStatus(false);
            return response;
        }

        response.setMsg("ok");
        response.setStatus(true);
        return response;
    }

    @Override
    public ResponseEntityData login(LoginRequest loginRequest) {
        ResponseEntityData responseEntityData = isValidLoginRequest(loginRequest);
        if (!responseEntityData.isStatus()) {
            return responseEntityData;
        }
        List<Long> userId = userRepository.findByUsernameAndPasswordAndStatus(
                loginRequest.getUsername(),
                EzySHA256.cryptUtfToLowercase(loginRequest.getPassword()),
                UserStatus.ACTIVE.getStatus()
        );
        if (Objects.isNull(userId) || userId.size() != 1) {
            logger.error("Thông tin đăng nhập không đúng");
            responseEntityData.setMsg("Thông tin đăng nhập không đúng");
            responseEntityData.setStatus(false);
            return responseEntityData;
        }
        String accessToken = "";
        try {
            accessToken = authenticationService.generateAccessToken(userId.get(0));
        } catch (Exception ex) {
            logger.error("Có lỗi khi tạo access token");
            responseEntityData.setMsg("Có lỗi khi tạo access token");
            responseEntityData.setStatus(false);
            return responseEntityData;
        }
        if (accessToken.isEmpty()) {
            responseEntityData.setMsg("accessToken có lỗi");
            responseEntityData.setStatus(false);
            return responseEntityData;
        }
        responseEntityData.setMsg(accessToken);
        responseEntityData.setStatus(true);

        return responseEntityData;
    }

}
