package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.entity.Deal;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.math.BigInteger;
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

    @EzyQuery(value = "select * from Deal e where 1 = 1 and " +
        " (?0 is null OR e.agency_id = ?0 )  and " +
        " (?1 is null OR e.candidate_id = ?1 ) and  " +
        " (?2 is null OR e.process_id = ?2 ) and  " +
        " (?3 is null OR e.status = ?3 ) " +
        " limit ?4 offset ?5 ", nativeQuery = true)
    List<Deal> searchDeal(Long agencyId, Long candidateId, Long processId,
                          EntityStatus status, int size, int skip);

    @EzyQuery(value = "select count(*) from Deal e where 1 = 1 and " +
        " (?0 is null OR e.agency_id = ?0 )  and" +
        " (?1 is null OR e.candidate_id = ?1 ) and  " +
        " (?2 is null OR e.process_id = ?2 ) and " +
        " (?3 is null OR e.status = ?3 ) ", nativeQuery = true)
    BigInteger totalSearchDeal(Long agencyId, Long candidateId, Long processId,
                                EntityStatus status);
}
