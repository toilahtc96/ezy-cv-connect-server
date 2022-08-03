package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.entity.UserType;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.util.List;

@EzyRepository
public interface UserTypeRepository extends EzyDatabaseRepository<Long, UserType> {

    @EzyQuery("select e from UserType e where e.code = ?0 ")
    List<UserType> getUserTypeByCode(String code);

    @EzyQuery("select e from UserType e where e.meaning = ?0 ")
    List<UserType> getUserTypeByMeaning(String meaning);

    @EzyQuery("select e from UserType e where e.status = ?0 ")
    List<UserType> findByStatus(int status);
}
