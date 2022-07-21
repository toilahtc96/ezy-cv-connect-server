package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.request.LoginRequest;
import com.ezyfox.cvconnect.request.RegisterRequest;

public interface UserService {

    void registerUser(RegisterRequest registerRequest);

    String login(LoginRequest registerRequest);
}
