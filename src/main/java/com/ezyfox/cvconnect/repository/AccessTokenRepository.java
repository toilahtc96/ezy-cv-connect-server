package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.entity.AccessToken;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

@EzyRepository
public interface AccessTokenRepository extends EzyDatabaseRepository<String, AccessToken> {
}
