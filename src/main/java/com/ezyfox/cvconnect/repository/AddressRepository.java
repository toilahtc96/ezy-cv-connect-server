package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.AddressType;
import com.ezyfox.cvconnect.entity.Address;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.math.BigInteger;
import java.util.List;

@EzyRepository
public interface AddressRepository extends EzyDatabaseRepository<Long, Address> {

    @EzyQuery("select count(e) from Address e where e.name like ?0 and e.type = ?1")
    long getCountAddressByNameStartAndType(String startOfName, String type);

    @EzyQuery("select e from Address e where e.type = ?0 ")
    List<Address> findAllByType(AddressType type);

    @EzyQuery("select e from Address e where e.parentId = ?0  ")
    List<Address> findAllByParentId(long parentId);

    @EzyQuery(value = "select * from address e where e.type = ?0 " +
            "and (province_code = ?1 or district_code = ?1) ", nativeQuery = true)
    List<Address> findAllByTypeAndParentCode(String type, String parentCode);

    @EzyQuery("select e from Address e where (e.provinceCode = ?0 or e.districtCode = ?0 " +
            "or e.precinctCode = ?0) and e.type=?1 and e.status ='ACTIVED' ")
    Address findByCodeAndType(String code, AddressType type);

    @EzyQuery(value = "select * from address e where e.type = ?0 limit ?2 offset ?1", nativeQuery = true)
    List<Address> findAllByTypePaging(String type, int offset, int size);

    @EzyQuery(value = "select * from Address e where 1 = 1 and " +
            " (?0 is null OR e.province_name like concat('%',?0,'%') ) and " +
            " (?1 is null OR e.province_code = ?1 ) and " +
            " (?2 is null OR e.district_name like concat('%',?2,'%') ) and " +
            " (?3 is null OR e.district_code = ?3 ) and " +
            " (?4 is null OR e.precinct_name like concat('%',?4,'%') ) and " +
            " (?5 is null OR e.precinct_code = ?5 ) and " +
            " (?6 is null OR e.type = ?6 ) and " +
            " (?7 is null OR e.status = ?7  ) " +
            " limit ?8 offset ?9 ", nativeQuery = true)
    List<Address> searchAddress(
            String provinceName,
            String provinceCode,
            String districtName,
            String districtCode,
            String precinctName,
            String precinctCode,
            String type,
            String status,
            int size,
            int skip
    );

    @EzyQuery(value = "select count(*) from Address e where 1 = 1 and " +
            " (?0 is null OR e.province_name like concat('%',?0,'%') ) and " +
            " (?1 is null OR e.province_code = ?1 ) and " +
            " (?2 is null OR e.district_name like concat('%',?2,'%') ) and " +
            " (?3 is null OR e.district_code = ?3 ) and " +
            " (?4 is null OR e.precinct_name like concat('%',?4,'%') ) and " +
            " (?5 is null OR e.precinct_code = ?5 ) and " +
            " (?6 is null OR e.type = ?6 ) and " +
            " (?7 is null OR e.status = ?7  ) ", nativeQuery = true)
    BigInteger totalSearchAddress(
            String provinceName,
            String provinceCode,
            String districtName,
            String districtCode,
            String precinctCode,
            String precinctName,
            String type,
            String status
    );
}
