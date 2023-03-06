package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.EditUserData;
import com.ezyfox.cvconnect.model.LoginData;
import com.ezyfox.cvconnect.model.RegisterData;
import com.ezyfox.cvconnect.model.SearchUserData;
import com.ezyfox.cvconnect.response.UserResponse;

import java.util.Map;

public interface UserService {

    void registerUser(RegisterData registerData);

    String login(LoginData registerData);

    Map<String, Object> getUserPaging(SearchUserData searchUserData);

    UserResponse getUserById(long id);

    void editUser(EditUserData editUserData);
}
