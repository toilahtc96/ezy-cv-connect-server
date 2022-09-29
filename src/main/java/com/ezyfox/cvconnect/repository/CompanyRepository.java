package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.entity.Company;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.util.List;

@EzyRepository
public interface CompanyRepository extends EzyDatabaseRepository<Long, Company> {
    
    @EzyQuery("Select e from Company e where e.provinceCode = ?0")
    List<Company> getByProvinceCode(String provinceCode);

    @EzyQuery("Select e from Company e where e.districtCode = ?0")
    List<Company> getByDistrictCode(String districtCode);

    @EzyQuery("Select e from Company e where e.precinctCode = ?0")
    List<Company> getByPrecinctCode(String precinctCode);

    @EzyQuery("Select e from Company e where e.star = ?0")
    List<Company> getByStar(int star);

    @EzyQuery("Select e from Company e where e.status = ?0")
    List<Company> getByStatus(EntityStatus status);

    @EzyQuery("Select e from Company e where e.code = ?0")
    List<Company> getByCode(String code);

    @EzyQuery("Select e from Company e where e.name = ?0")
    List<Company> getByName(String name);
}
