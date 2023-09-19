package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.entity.Progress;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@EzyRepository
public interface ProgressRepository extends EzyDatabaseRepository<Long, Progress> {

    @EzyQuery("select e from Progress e where e.agencyId = ?0")
    List<Progress> getByAgencyId(long agencyId);

    @EzyQuery("select e from Progress e where e.candidateId = ?0")
    List<Progress> getByCandidateId(long candidateId);

    @EzyQuery("select e from Progress e where e.status = ?0")
    List<Progress> getByStatus(EntityStatus status);

    @EzyQuery(value = "select * from Progress p where p.agency_id = ?0 and p.status = ?1 and " +
            " (?2 is null or p.job_id = ?2) and " +
            " (?3 is null or p.candidate_send_cv_time >= ?3) and " +
            " (?4 is null or p.candidate_send_cv_time <= ?4) and " +
            " (?5 is null or p.send_cv_company_time >= ?5) and " +
            " (?6 is null or p.send_cv_company_time <= ?6) " +
            " limit ?7 offset ?8 ", nativeQuery = true)
    List<Progress> getActiveProgressByAgencyId(
        long agencyId,
        String status,
        Long companyId,
        Date fromCandidateTimeToDate,
        Date toCandidateTimeToDate,
        Date sendCompanyFromToDate,
        Date sendCompanyToToDate,
        int size,
        int skip
    );

    @EzyQuery(value = "select count(*) from Progress where agency_id = ?0 and status = ?1 ", nativeQuery = true)
    BigInteger totalActiveProgressByAgencyId(long agencyId, String status);

    @EzyQuery(value = "select * from Progress e where 1 = 1 and " +
        " (?0 is null OR e.agency_id = ?0 )  and " +
        " (?1 is null OR e.candidate_id = ?1 ) and  " +
        " (?2 is null OR e.step_id = ?2 ) and  " +
        " (?3 is null OR e.status = ?3 ) " +
        " limit ?4 offset ?5 ", nativeQuery = true)
    List<Progress> searchProgress(Long agencyId, Long candidateId, Long stepId,
                          EntityStatus status, int size, int skip);

    @EzyQuery(value = "select count(*) from Progress e where 1 = 1 and " +
        " (?0 is null OR e.agency_id = ?0 )  and" +
        " (?1 is null OR e.candidate_id = ?1 ) and  " +
        " (?2 is null OR e.step_id = ?2 ) and " +
        " (?3 is null OR e.status = ?3 ) ", nativeQuery = true)
    BigInteger totalSearchProgress(Long agencyId, Long candidateId, Long stepId,
                                EntityStatus status);


    @EzyQuery(value = "select * from Progress e where 1=1 and " +
            " (?0 is null OR e.candidate_id = ?0) and " +
            " (?1 is null OR e.job_id = ?1 ) ", nativeQuery = true)
    List<Progress> getAllByAndCandidateIdAndJobId(long candidateId, long jobId);

    @EzyQuery("select e from Progress e where e.candidateId = ?0 and e.agencyId = ?1 and e.jobId = ?2 and e.status = ?3")
    Progress getActiveProgressByCandidateAndAgencyAndJob(long candidateId,long agencyId,long jobId, EntityStatus status);

}
