package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.ProcessCode;
import com.ezyfox.cvconnect.entity.Process;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.util.List;

@EzyRepository
public interface ProcessRepository extends EzyDatabaseRepository<Long, Process> {

    @EzyQuery("select e from Process e where e.code = ?0")
    List<Process> findByProcessCode(ProcessCode processCode);

    @EzyQuery("select e from Process e where e.status = ?0")
    List<Process> findByStatus(EntityStatus entityStatus);
}
