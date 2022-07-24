package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.converter.ModelToEntityConverter;
import com.ezyfox.cvconnect.entity.AccessToken;
import com.ezyfox.cvconnect.exception.TokenExpiredException;
import com.ezyfox.cvconnect.exception.TokenNotFoundException;
import com.ezyfox.cvconnect.repository.AccessTokenRepository;
import com.ezyfox.cvconnect.service.AuthenticationService;
import com.tvd12.ezyfox.annotation.EzyProperty;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

import java.time.LocalDateTime;

@EzySingleton
public class AuthenticationServiceImpl implements AuthenticationService {

    @EzyProperty("access_token.expires_in")
    private int expireIn;
    @EzyAutoBind
    private AccessTokenRepository accessTokenRepository;
    @EzyAutoBind
    private ModelToEntityConverter modelToEntityConverter;

    @Override
    public long verifyAccessToken(String accessToken) {
        LocalDateTime now = LocalDateTime.now();
        AccessToken accessTokenObj = accessTokenRepository.findById(accessToken);
        if (accessTokenObj == null) {
            throw new TokenNotFoundException("token: " + accessToken + " not found");
        } else if (accessTokenObj.getExpireIn().isBefore(now)) {
            throw new TokenExpiredException("token: " + accessToken + " expired");
        }
        return accessTokenObj.getUserId();
    }

    @Override
    public String generateAccessToken(long userId) {
        AccessToken accessToken = modelToEntityConverter.toAccessTokenEntity(userId, expireIn);
        accessTokenRepository.save(accessToken);
        return accessToken.getAccessToken();
    }

    @Override
    public void removeAccessToken(String accessToken) {
        accessTokenRepository.delete(accessToken);
    }
}
