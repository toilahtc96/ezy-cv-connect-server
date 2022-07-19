package com.ezyfox.cvconnect.services;

public interface IAuthenticationService {
    long verifyAccessToken(String accessToken);

    String generateAccessToken(long userId);

    void removeAccessToken(String accessToken);
}
