package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.entity.Company;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.math.BigInteger;
import java.util.List;

@EzyRepository
public interface CompanyRepository extends EzyDatabaseRepository<Long, Company> {

    @EzyQuery("select e from Company e where e.provinceCode = ?0")
    List<Company> getByProvinceCode(String provinceCode);

    @EzyQuery("select e from Company e where e.districtCode = ?0")
    List<Company> getByDistrictCode(String districtCode);

    @EzyQuery("select e from Company e where e.precinctCode = ?0")
    List<Company> getByPrecinctCode(String precinctCode);

    @EzyQuery("select e from Company e where e.star = ?0")
    List<Company> getByStar(int star);

    @EzyQuery("select e from Company e where e.status = ?0")
    List<Company> getByStatus(EntityStatus status);

    @EzyQuery("select e from Company e where e.code = ?0")
    List<Company> getByCode(String code);

    @EzyQuery("select e from Company e where e.name = ?0")
    List<Company> getByName(String name);

    @EzyQuery(value = "select * from Company e where 1 = 1 and " +
            " (?0 is null OR e.name like concat('%',?0,'%')  )  and" +
            " (?1 is null OR e.code like concat('%',?1,'%') ) and " +
            " (?2 is null OR e.province_code = ?2 ) and  " +
            " (?3 is null OR e.district_code = ?3  ) and " +
            " (?4 is null OR e.precinct_code = ?4  ) " +
            " limit ?5 offset ?6 ", nativeQuery = true)
    List<Company> searchCompany(
            String companyName,
            String companyCode,
            String provinceCode,
            String districtCode,
            String precinctCode,
            int size,
            int skip
            );

    @EzyQuery(value = "select count(*) from Company e where 1 = 1 and " +
            " (?0 is null OR e.name like concat('%',?0,'%')  )  and" +
            " (?1 is null OR e.code like concat('%',?1,'%') ) and " +
            " (?2 is null OR e.province_code = ?2 ) and  " +
            " (?3 is null OR e.district_code = ?3  ) and " +
            " (?4 is null OR e.precinct_code = ?4  ) " , nativeQuery = true)
    BigInteger totalSearchCompany(
            String companyName,
            String companyCode,
            String provinceCode,
            String districtCode,
            String precinctCode
    );
}
