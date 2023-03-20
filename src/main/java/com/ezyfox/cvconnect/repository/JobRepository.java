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
        " (?0  is null OR e.job_type_id = ?0  ) and " +
        " (?1  is null OR e.company_id = ?1  ) and " +
        " (?2  is null OR e.range_salary_min <= ?2  ) and " +
        " (?2  is null OR e.range_salary_max >= ?2  ) and " +
        " (?3  is null OR e.level_id = ?3  ) and " +
        " (?4  is null OR e.custom_range = ?4  ) and " +
        " (?5  is null OR e.career_id = ?5  ) and " +
        " (?6  is null OR e.working_form_id = ?6  ) and " +
        " (?7  is null OR e.status = ?7  ) and " +
        " (?8 is null OR e.information like concat('%',?8,'%')) " +
        " limit ?9 offset ?10 ", nativeQuery = true)
    List<Job> searchJob(
        Long jobTypeId,
        Long companyId,
        Double rangeSalary,
        Long levelId,
        Boolean customRange,
        Long careerId,
        Long workingFormId,
        String status,
        String information,
        int page,
        int size
    );

    @EzyQuery(value = "select count(*) from Job e where 1 = 1 and " +
        " (?0  is null OR e.job_type_id = ?0  ) and " +
        " (?1  is null OR e.company_id = ?1  ) and " +
        " (?2  is null OR e.range_salary_min <= ?2  ) and " +
        " (?2  is null OR e.range_salary_max >= ?2  ) and " +
        " (?3  is null OR e.level_id = ?3  ) and " +
        " (?4  is null OR e.custom_range = ?4  ) and " +
        " (?5  is null OR e.career_id = ?5  ) and " +
        " (?6  is null OR e.working_form_id = ?6  ) and " +
        " (?7  is null OR e.status = ?7  ) and " +
        " (?8 is null OR e.information like concat('%',?8,'%')) " , nativeQuery = true)
    BigInteger totalSearchJob(
        Long jobTypeId,
        Long companyId,
        Double rangeSalary,
        Long levelId,
        Boolean customRange,
        Long careerId,
        Long workingFormId,
        String status,
        String information
    );
}
