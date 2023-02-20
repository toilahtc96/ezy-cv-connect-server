package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.UserTypeCode;
import com.ezyfox.cvconnect.entity.UserType;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.math.BigInteger;
import java.util.List;

@EzyRepository
public interface UserTypeRepository extends EzyDatabaseRepository<Long, UserType> {

    @EzyQuery("select e from UserType e where e.code = ?0 ")
    UserType getUserTypeByCode(UserTypeCode code);

    @EzyQuery("select e from UserType e where e.meaning = ?0 ")
    List<UserType> getUserTypeByMeaning(String meaning);

    @EzyQuery("select e from UserType e where e.status = ?0 ")
    List<UserType> findByStatus(EntityStatus status);

    @EzyQuery(value = "select * from User_Type e where 1 = 1 and " +
            " (?0 is null OR e.meaning like concat('%',?0,'%')  )  and" +
            " (?1 is null OR e.code = ?1 ) and  " +
            " (?2 is null OR e.status = ?2 ) " +
            " limit ?3 offset ?4 ", nativeQuery = true)
    List<UserType> searchUserType(String meaning, String code, EntityStatus status, int size, int skip);

    @EzyQuery(value = "select count(*) from User_Type e where 1 = 1 and " +
            " (?0 is null OR e.meaning like concat('%',?0,'%')  )  and" +
            " (?1 is null OR e.code = ?1 ) and  " +
            " (?2 is null OR e.status = ?2 ) ", nativeQuery = true)
    BigInteger totalSearchUserType(String meaning, String code, EntityStatus status);
}
