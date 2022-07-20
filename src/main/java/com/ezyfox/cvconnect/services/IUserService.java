package com.ezyfox.cvconnect.services;

import com.ezyfox.cvconnect.request.RegisterRequest;
import com.ezyfox.cvconnect.response.ResponseEntityData;

public interface IUserService {
    ResponseEntityData isValidRegisterRequest(RegisterRequest registerRequest);

    ResponseEntityData registerUser(RegisterRequest registerRequest);
}
