package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.entity.UserRole;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.util.List;

@EzyRepository
public interface UserRoleRepository extends EzyDatabaseRepository<Long, UserRole> {

    @EzyQuery("select e from UserRole e")
    List<UserRole> findAll();

    @EzyQuery("select e from UserRole e where roleId = ?0")
    List<UserRole> findByRoleId(long roleId);

    @EzyQuery("select e from UserRole e where id = ?0")
    UserRole findById(long userRoleId);

    @EzyQuery("select e from UserRole e where userId = ?0")
    List<UserRole> findByUserId(long userId);

    @EzyQuery("select e from UserRole e where e.status = ?0")
    List<UserRole> findAllActive(EntityStatus status);

}
