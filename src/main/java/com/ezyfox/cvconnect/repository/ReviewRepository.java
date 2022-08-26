package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.entity.Review;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.util.List;

@EzyRepository
public interface ReviewRepository extends EzyDatabaseRepository<Long, Review> {

    @EzyQuery("select e from Review e where object_id =?0")
    List<Review> getByObjectId(long objectId);

    @EzyQuery("select e from Review e where type =?0")
    List<Review> getByType(int type);

    @EzyQuery("select e from Review e where status =?0")
    List<Review> getByStatus(EntityStatus status);

    @EzyQuery("select e from Review e where star =?0")
    List<Review> getByStar(int star);
}
