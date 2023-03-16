package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.entity.JobType;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

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
}
