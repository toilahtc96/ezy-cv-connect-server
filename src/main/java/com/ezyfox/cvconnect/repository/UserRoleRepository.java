package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.entity.UserRole;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

@EzyRepository
public interface UserRoleRepository extends EzyDatabaseRepository<Long, UserRole> {
}
