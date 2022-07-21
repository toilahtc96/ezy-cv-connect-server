package com.ezyfox.cvconnect.validator;

import com.ezyfox.cvconnect.entity.User;
import com.ezyfox.cvconnect.request.LoginRequest;
import com.ezyfox.cvconnect.request.RegisterRequest;
import com.ezyfox.cvconnect.response.ResponseEntityData;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;

import java.util.List;
import java.util.Objects;

@EzySingleton
public class UserValidator {
    public void validRegisterRequest(RegisterRequest registerRequest) {
        ResponseEntityData response = new ResponseEntityData();
        if (Objects.isNull(registerRequest)) {
            throw new HttpBadRequestException("Thiếu thông tin đăng kí");
        }
        if (Objects.isNull(registerRequest.getName())) {
            throw new HttpBadRequestException("Thiếu thông tin tên người sử dụng");
        }
        if (Objects.isNull(registerRequest.getUsername())) {
            throw new HttpBadRequestException("Thiếu thông tin tên đăng nhập");
        }
        if (Objects.isNull(registerRequest.getPassword())) {
            throw new HttpBadRequestException("Thiếu thông tin mật khẩu");
        }
        if (Objects.isNull(registerRequest.getTypeId())) {
            throw new HttpBadRequestException("Thiếu thông tin loại hình đăng kí");
        }

    }

    public void validLoginRequest(LoginRequest loginRequest) {
        if (Objects.isNull(loginRequest)) {
            throw new HttpBadRequestException("Thiếu thông tin đăng nhập");
        }
        if (Objects.isNull(loginRequest.getUsername())) {
            throw new HttpBadRequestException("Thiếu thông tin tên đăng nhập");
        }
        if (Objects.isNull(loginRequest.getPassword())) {
            throw new HttpBadRequestException("Thiếu thông tin mật khẩu");
        }
    }

    public void validUsernameAndPassword(User userByUsername) {
        if (Objects.nonNull(userByUsername)) {
            throw new HttpBadRequestException("Tên người dùng đã tồn tại");
        }
    }

    public void validUserId(List<Long> userId) {
        if (Objects.isNull(userId) || userId.size() != 1) {
            throw new HttpBadRequestException("Thông tin đăng nhập không đúng");
        }
    }
}
