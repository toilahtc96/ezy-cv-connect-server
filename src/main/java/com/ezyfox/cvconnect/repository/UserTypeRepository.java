package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.entity.UserType;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

@EzyRepository
public interface UserTypeRepository extends EzyDatabaseRepository<Long, UserType> {
}
