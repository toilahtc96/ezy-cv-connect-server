package com.ezyfox.cvconnect.services;

import com.ezyfox.cvconnect.request.LoginRequest;
import com.ezyfox.cvconnect.request.RegisterRequest;
import com.ezyfox.cvconnect.response.ResponseEntityData;

public interface IUserService {
    ResponseEntityData isValidRegisterRequest(RegisterRequest registerRequest);

    ResponseEntityData isValidLoginRequest(LoginRequest loginRequest);
    ResponseEntityData registerUser(RegisterRequest registerRequest);
    ResponseEntityData login(LoginRequest registerRequest);

}
