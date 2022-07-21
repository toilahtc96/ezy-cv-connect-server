package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.entity.User;
import com.ezyfox.cvconnect.request.LoginRequest;
import com.ezyfox.cvconnect.request.RegisterRequest;
import com.ezyfox.cvconnect.response.ResponseEntityData;

public interface IUserService {

    void registerUser(RegisterRequest registerRequest);

    String login(LoginRequest registerRequest);
}
