package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.RoleCode;
import com.ezyfox.cvconnect.entity.Role;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.util.List;

@EzyRepository
public interface RoleRepository extends EzyDatabaseRepository<Long, Role> {

    @EzyQuery("select e from Role e where e.code = ?0 and status = ?1")
    List<Role> getRoleByCodeAndStatus(RoleCode code, EntityStatus status);

    @EzyQuery("select e from Role e where e.name = ?0 and status = ?1")
    List<Role> getRoleByNameAndStatus(String name, EntityStatus status);

    @EzyQuery("select e from Role e where e.status = ?0")
    List<Role> getAllRoleByStatus(EntityStatus status);

    List<Role> getAllRole();
}
