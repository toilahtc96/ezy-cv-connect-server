package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.entity.Review;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

@EzyRepository
public interface ReviewRepository extends EzyDatabaseRepository<Long, Review> {
}
