package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.entity.User;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

@EzyRepository
public interface UserRepository extends EzyDatabaseRepository<Long, User> {
}
