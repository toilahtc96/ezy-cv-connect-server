package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.entity.Career;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.math.BigInteger;
import java.util.List;

@EzyRepository
public interface CareerRepository extends EzyDatabaseRepository<Long, Career> {

    @EzyQuery("select e from Career e where e.code = ?0 and status = ?1")
    List<Career> getCareerByCodeAndStatus(String code, EntityStatus status);

    @EzyQuery("select e from Career e where e.name = ?0 and status = ?1")
    List<Career> getCareerByNameAndStatus(String name, EntityStatus status);

    @EzyQuery("select e from Career e where e.status = ?0")
    List<Career> getAllCareerByStatus(EntityStatus status);

    List<Career> getAllCareer();
    @EzyQuery(value = "select * from career e where 1 = 1 and " +
        " (?0 is null OR e.name like concat('%',?0,'%')) " +
        " limit ?1 offset ?2 ", nativeQuery = true)
    List<Career> searchCareer(
        String name,
        int size,
        int skip
    );

    @EzyQuery(value = "select count(*) from career e where 1 = 1 and " +
        " (?0 is null OR e.name like concat('%',?0,'%')) ", nativeQuery = true)
    BigInteger totalSearchCareer(
        String name
    );
}
