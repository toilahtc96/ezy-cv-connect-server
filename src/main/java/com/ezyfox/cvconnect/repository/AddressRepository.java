package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.AddressType;
import com.ezyfox.cvconnect.entity.Address;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.util.List;

@EzyRepository
public interface AddressRepository extends EzyDatabaseRepository<Long, Address> {

    @EzyQuery("select count(e) from Address e where e.name like ?0 and e.type = ?1")
    long getCountAddressByNameStartAndType(String startOfName, AddressType type);

    @EzyQuery("select e from Address e where e.type = ?0 and e.status = 1")
    List<Address> findAllByType(AddressType type);

    @EzyQuery("select e from Address e where e.parentId = ?0  and e.status = 1 ")
    List<Address> findAllByParentId(long parentId);

    @EzyQuery("select e from Address e where e.type = ?0 and e.parentId = ?1  and e.status = 1 ")
    List<Address> findAllByTypeAndParentId(AddressType type, long parentId);

}
