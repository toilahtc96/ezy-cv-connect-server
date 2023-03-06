package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.ProcessCode;
import com.ezyfox.cvconnect.entity.Process;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.math.BigInteger;
import java.util.List;

@EzyRepository
public interface ProcessRepository extends EzyDatabaseRepository<Long, Process> {

    @EzyQuery("select e from Process e where e.code = ?0")
    List<Process> findByProcessCode(ProcessCode processCode);

    @EzyQuery("select e from Process e where e.status = ?0")
    List<Process> findByStatus(EntityStatus entityStatus);

    @EzyQuery(value = "select * from Process e where 1 = 1 and " +
            " (?0 is null OR e.code = ?0 )  and" +
            " (?1 is null OR e.meaning like concat('%',?1,'%')  ) and  " +
            " (?2 is null OR e.status = ?2 )  " +
            " limit ?3 offset ?4 ", nativeQuery = true)
    List<Process> searchProcess(ProcessCode code, String meaning,
                          EntityStatus status, int size, int skip);

    @EzyQuery(value = "select count(*) from Process e where 1 = 1 and " +
            " (?0 is null OR e.code = ?0 )  and" +
            " (?1 is null OR e.meaning like concat('%',?1,'%')  ) and  " +
            " (?2 is null OR e.status = ?2 ) ", nativeQuery = true)
    BigInteger totalSearchProcess(ProcessCode code, String meaning,
                                  EntityStatus status);
}
