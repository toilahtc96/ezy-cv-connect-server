package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.entity.Review;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.math.BigInteger;
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

    @EzyQuery(value = "select * from Review e where 1=1 and " +
            " ?0 is null OR e.object_id = ?0 AND " +
            " ?1 is null OR e.review_user_id = ?1 order by star desc " +
            " limit ?2 offset ?3 ", nativeQuery = true)
    List<Review> findReview(Long objectId, Long userReviewId, int size, int skip);

    @EzyQuery(value = "select count(*) from Review e where 1=1 and " +
            " (?0 is null OR e.object_id = ?0) AND " +
            " (?1 is null OR e.review_user_id = ?1) ", nativeQuery = true)
    BigInteger sumOfReview(Long objectId, Long userReviewId);

    @EzyQuery(value = "select e.star from Review e where 1=1 and " +
            " ?0 is null OR e.object_id = ?0 AND " +
            " ?1 is null OR e.review_user_id = ?1 ", nativeQuery = true)
    List<Integer> getAllStarOfObjectOrUser(Long objectId, Long userReviewId);

    @EzyQuery(value = "select count(*) from Review e where 1=1 AND " +
            " (?0 is null OR e.object_id = ?0) AND " +
            " (?1 is null OR e.review_user_id = ?1) AND " +
            " (?2 is null OR e.star = ?2) ", nativeQuery = true)
    BigInteger sumOfReviewByStar(Long objectId, Long userReviewId, int star);
}
