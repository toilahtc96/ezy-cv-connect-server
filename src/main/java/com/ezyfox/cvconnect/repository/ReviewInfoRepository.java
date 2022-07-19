package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.entity.ReviewInfo;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

@EzyRepository
public interface ReviewInfoRepository extends EzyDatabaseRepository<Long, ReviewInfo> {
}
