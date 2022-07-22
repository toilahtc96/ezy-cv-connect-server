package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.LoginData;
import com.ezyfox.cvconnect.model.RegisterData;

public interface UserService {

    void registerUser(RegisterData registerData);

    String login(LoginData registerData);
}
