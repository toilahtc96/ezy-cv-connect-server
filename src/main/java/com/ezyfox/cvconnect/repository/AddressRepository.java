package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.AddressType;
import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.entity.Address;
import com.ezyfox.cvconnect.entity.Company;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.math.BigInteger;
import java.util.List;

@EzyRepository
public interface AddressRepository extends EzyDatabaseRepository<Long, Address> {

  @EzyQuery("select count(e) from Address e where e.name like ?0 and e.type = ?1")
  long getCountAddressByNameStartAndType(String startOfName, AddressType type);

  @EzyQuery("select e from Address e where e.type = ?0 ")
  List<Address> findAllByType(AddressType type);

  @EzyQuery("select e from Address e where e.parentId = ?0  ")
  List<Address> findAllByParentId(long parentId);

  @EzyQuery("select e from Address e where e.type = ?0 and e.parentId = ?1 ")
  List<Address> findAllByTypeAndParentId(AddressType type, long parentId);

  @EzyQuery("select e from Address e where e.code = ?0 and e.type=?1 and e.status ='ACTIVED' ")
  Address findByCodeAndType(String code, AddressType type);

  @EzyQuery("select e from Address e where LOWER(e.name) = LOWER(?0) and e.type=?1 and e.status ='ACTIVED' ")
  Address findByNameAndType(String name, AddressType type);


  @EzyQuery(value = "select * from address e where e.type = ?0 limit ?2 offset ?1", nativeQuery = true)
  List<Address> findAllByTypePaging(String type, int offset, int size);

  @EzyQuery(value = "select * from Address e where 1 = 1 and " +
      " (?0 is null OR e.name like concat('%',?0,'%') ) and " +
      " (?1 is null OR e.status = ?1  ) " +
      " limit ?2 offset ?3 ", nativeQuery = true)
  List<Address> searchAddress(
      String name,
      String status,
      int size,
      int skip
  );

  @EzyQuery(value = "select count(*) from Address e where 1 = 1 and " +
      " (?0 is null OR e.name  like concat('%',?0,'%') ) and " +
      " (?1 is null OR e.status = ?1  ) ", nativeQuery = true)
  BigInteger totalSearchAddress(
      String name,
      String status
  );
}
