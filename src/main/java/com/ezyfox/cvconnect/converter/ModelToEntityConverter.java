package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.entity.AccessToken;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.security.EzySHA256;

import java.time.LocalDateTime;
import java.util.UUID;

@EzySingleton
public class ModelToEntityConverter {

    public AccessToken toAccessTokenEntity(long userId, int expireIn) {
        String tokenString = EzySHA256.cryptUtfToLowercase(
            userId + UUID.randomUUID().toString() + System.currentTimeMillis()
        );
        LocalDateTime now = LocalDateTime.now();
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(tokenString);
        accessToken.setExpireAt(now);
        accessToken.setExpireIn(now.plusSeconds(expireIn));
        accessToken.setFirstIssueAt(now);
        accessToken.setUserId(userId);
        accessToken.setCreatedTime(now);
        return accessToken;
    }
}
