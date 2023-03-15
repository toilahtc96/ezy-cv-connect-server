package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.*;
import com.ezyfox.cvconnect.response.LoginResponse;
import com.ezyfox.cvconnect.response.UserResponse;

import java.util.Map;

public interface UserService {
    void registerUserAdmin(RegisterData registerData);

    void registerUser(UserRegisterData userRegisterData);

    LoginResponse login(LoginData registerData);

    Map<String, Object> getUserPaging(SearchUserData searchUserData);

    UserResponse getUserById(long id);

    void editUser(EditUserData editUserData);

    UserResponse getByToken(String accessToken);
}
