package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.entity.AccessToken;
import com.ezyfox.cvconnect.exception.TokenExpiredException;
import com.ezyfox.cvconnect.exception.TokenNotFoundException;
import com.ezyfox.cvconnect.repository.AccessTokenRepository;
import com.ezyfox.cvconnect.service.IAuthenticationService;
import com.tvd12.ezyfox.annotation.EzyProperty;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.sercurity.EzySHA256;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@EzySingleton
public class AuthenticationServiceImpl implements IAuthenticationService {
    @EzyProperty("access_token.expires_in")
    private int expireIn;

    @EzyAutoBind
    private AccessTokenRepository accessTokenRepository;

    @Override
    public long verifyAccessToken(String accessToken) {
        LocalDateTime now = LocalDateTime.now();
        AccessToken accessTokenObj = accessTokenRepository.findById(accessToken);
        if (Objects.isNull(accessTokenObj)) {
            throw new TokenNotFoundException("token: " + accessToken + " not found");
        } else if (accessTokenObj.getExpireIn().isBefore(now)) {
            throw new TokenExpiredException("token: " + accessToken + " expired");
        }

        return accessTokenObj.getUserId();
    }

    @Override
    public String generateAccessToken(long userId) {
        String tokenString = EzySHA256.cryptUtfToLowercase(
                userId + UUID.randomUUID().toString() + System.currentTimeMillis()
        );
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(tokenString);
        accessToken.setExpireAt(LocalDateTime.now());
        accessToken.setExpireIn(LocalDateTime.now().plusSeconds(expireIn));
        accessToken.setFirstIssueAt(LocalDateTime.now());
        accessToken.setUserId(userId);
        accessToken.setCreatedTime(LocalDateTime.now());
        accessTokenRepository.save(accessToken);
        return tokenString;
    }

    @Override
    public void removeAccessToken(String accessToken) {
        accessTokenRepository.delete(accessToken);
    }
}
