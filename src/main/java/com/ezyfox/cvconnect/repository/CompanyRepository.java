package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.entity.Company;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

@EzyRepository
public interface CompanyRepository extends EzyDatabaseRepository<Long, Company> {
}
