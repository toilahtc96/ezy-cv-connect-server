package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.entity.Company;
import com.ezyfox.cvconnect.entity.JobType;
import com.ezyfox.cvconnect.entity.WorkingForm;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.math.BigInteger;
import java.util.List;

@EzyRepository
public interface WorkingFormRepository extends EzyDatabaseRepository<Long, WorkingForm> {

    @EzyQuery("select e from WorkingForm e where e.code = ?0 and status = ?1")
    List<WorkingForm> getWorkingFormByCodeAndStatus(String code, EntityStatus status);

    @EzyQuery("select e from WorkingForm e where e.name = ?0 and status = ?1")
    List<WorkingForm> getWorkingFormByNameAndStatus(String name, EntityStatus status);

    @EzyQuery("select e from WorkingForm e where e.status = ?0")
    List<WorkingForm> getAllWorkingFormByStatus(EntityStatus status);

    List<WorkingForm> getAllWorkingForm();

    @EzyQuery(value = "select * from working_form e where 1 = 1 and " +
            " (?0 is null OR e.name like concat('%',?0,'%')) " +
            " limit ?1 offset ?2 ", nativeQuery = true)
    List<WorkingForm> searchWorkingForm(
            String name,
            int size,
            int skip
    );

    @EzyQuery(value = "select count(*) from working_form e where 1 = 1 and " +
            " (?0 is null OR e.name like concat('%',?0,'%')) ", nativeQuery = true)
    BigInteger totalSearchWorkingForm(
            String name
    );
}
