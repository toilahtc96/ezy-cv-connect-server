package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.entity.User;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.util.List;

@EzyRepository
public interface UserRepository extends EzyDatabaseRepository<Long, User> {

    @EzyQuery("select max(id+1) from User e")
    Long getMaxUserId();

    @EzyQuery("select e.id from User e where e.username = ?0 and e.password = ?1 and e.status = ?2")
    List<Long> findByUsernameAndPasswordAndStatus(String username, String password, UserStatus status);
}

