package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.entity.Deal;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

@EzyRepository
public interface DealRepository extends EzyDatabaseRepository<Long, Deal> {
}
