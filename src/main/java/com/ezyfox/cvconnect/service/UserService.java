package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.LoginData;
import com.ezyfox.cvconnect.model.RegisterData;
import com.ezyfox.cvconnect.model.SearchUserData;
import com.ezyfox.cvconnect.response.UserResponse;

import java.util.List;

public interface UserService {

    void registerUser(RegisterData registerData);

    String login(LoginData registerData);

    List<UserResponse> getUserPaging(SearchUserData searchUserData);
}
