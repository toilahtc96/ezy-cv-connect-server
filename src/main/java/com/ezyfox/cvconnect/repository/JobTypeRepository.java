package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.entity.JobType;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.math.BigInteger;
import java.util.List;

@EzyRepository
public interface JobTypeRepository extends EzyDatabaseRepository<Long, JobType> {

    @EzyQuery("select e from JobType e where e.code = ?0 and status = ?1")
    List<JobType> getJobTypeByCodeAndStatus(String code, EntityStatus status);

    @EzyQuery("select e from JobType e where e.name = ?0 and status = ?1")
    List<JobType> getJobTypeByNameAndStatus(String name, EntityStatus status);

    @EzyQuery("select e from JobType e where e.status = ?0")
    List<JobType> getAllJobTypeByStatus(EntityStatus status);

    List<JobType> getAllJobType();


    @EzyQuery(value = "select * from job_type e where 1 = 1 and " +
        " (?0 is null OR e.name like concat('%',?0,'%')) " +
        " limit ?1 offset ?2 ", nativeQuery = true)
    List<JobType> searchJobType(
        String name,
        int size,
        int skip
    );

    @EzyQuery(value = "select count(*) from job_type e where 1 = 1 and " +
        " (?0 is null OR e.name like concat('%',?0,'%')) ", nativeQuery = true)
    BigInteger totalSearchJobType(
        String name
    );
}
