package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.entity.Company;
import com.ezyfox.cvconnect.entity.Job;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.math.BigInteger;
import java.util.List;

@EzyRepository
public interface JobRepository extends EzyDatabaseRepository<Long, Job> {

    @EzyQuery(value = "select * from Job e where 1 = 1 and " +
        " (?0  is null OR e.status = ?0  ) and " +
        " (?1 is null OR e.information like concat('%',?1,'%')) " +
        " limit ?2 offset ?3 ", nativeQuery = true)
    List<Job> searchJob(
        String status,
        String information,
        int size,
        int skip
    );

    @EzyQuery(value = "select count(*) from Job e where 1 = 1 and " +
        " (?0  is null OR e.status = ?0  ) and " +
        " (?1 is null OR e.information like concat('%',?1,'%')) "
        , nativeQuery = true)
    BigInteger totalSearchJob(
        String status,
        String information
    );
}
