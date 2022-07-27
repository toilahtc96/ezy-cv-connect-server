package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.entity.Address;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

@EzyRepository
public interface AddressRepository extends EzyDatabaseRepository<Long, Address> {

    @EzyQuery("select count(e) from Address e where e.name like ?0 and e.type = ?1")
    long getCountAddressByNameStartAndType(String startOfName, long type);

}
