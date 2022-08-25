package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.entity.Deal;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.util.List;

@EzyRepository
public interface DealRepository extends EzyDatabaseRepository<Long, Deal> {

    @EzyQuery("select e from Deal e where e.agencyId = ?0")
    List<Deal> getByAgencyId(long agencyId);

    @EzyQuery("select e from Deal e where e.candidateId = ?0")
    List<Deal> getByCandidateId(long candidateId);

    @EzyQuery("select e from Deal e where e.status = ?0")
    List<Deal> getByStatus(EntityStatus status);

    @EzyQuery("select e from Deal e where e.agencyId = ?0 and e.status = ?1")
    List<Deal> getActiveDealByAgencyId(long agencyId, EntityStatus status);
}
