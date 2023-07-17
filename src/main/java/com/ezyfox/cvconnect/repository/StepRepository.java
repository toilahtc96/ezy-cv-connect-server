package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.StepCode;
import com.ezyfox.cvconnect.entity.Step;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.math.BigInteger;
import java.util.List;

@EzyRepository
public interface StepRepository extends EzyDatabaseRepository<Long, Step> {

    @EzyQuery("select e from Step e where e.code = ?0")
    List<Step> findByStepCode(StepCode stepCode);

    @EzyQuery("select e from Step e where e.status = ?0")
    List<Step> findByStatus(EntityStatus entityStatus);

    @EzyQuery(value = "select * from Step e where 1 = 1 and " +
            " (?0 is null OR e.code = ?0 )  and" +
            " (?1 is null OR e.meaning like concat('%',?1,'%')  ) and  " +
            " (?2 is null OR e.status = ?2 )  " +
            " limit ?3 offset ?4 ", nativeQuery = true)
    List<Step> searchStep(StepCode code, String meaning,
                          EntityStatus status, int size, int skip);

    @EzyQuery(value = "select count(*) from Step e where 1 = 1 and " +
            " (?0 is null OR e.code = ?0 )  and" +
            " (?1 is null OR e.meaning like concat('%',?1,'%')  ) and  " +
            " (?2 is null OR e.status = ?2 ) ", nativeQuery = true)
    BigInteger totalSearchStep(StepCode code, String meaning,
                                  EntityStatus status);
}
