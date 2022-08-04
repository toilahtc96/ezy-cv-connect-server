package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.entity.User;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.util.List;

@EzyRepository
public interface UserRepository extends EzyDatabaseRepository<Long, User> {

    @EzyQuery("select max(id+1) from User e")
    Long getMaxUserId();

    @EzyQuery("select e from User e where e.username = ?0")
    List<User> findByUsername(String username);

    @EzyQuery("select e from User e where e.typeId = ?0")
    List<User> getByTypeId(long typeId);

    @EzyQuery("select e from User e where e.typeId =?0 abd status =?1")
    List<User> getAllByTypeIdAndStatus(long typeId, int status);

    @EzyQuery("select e from User e where e.typeId =?0 and status =?1 and companyId =?2")
    List<User> getAllByTypeIdAndStatusAndCompanyId(long typeId, int status, Long companyId);

    @EzyQuery("select e from User e where e.typeId =?0 and companyId =?1")
    List<User> getAllByTypeIdAndCompanyId(long typeId, long companyId);


    @EzyQuery("select e from User e where e.typeId =?0 and roleId =?1")
    List<User> getAllByTypeAndRoleId(long typeId, long roleId);

    @EzyQuery("select e from User e where e.typeId =?0 and status =?1 and roleId =?2")
    List<User> getAllByTypeAndStatusAndRoleId(long typeId, int status, long roleId);

    @EzyQuery("select e from User e where e.typeId = ?0 and e.id =?1 ")
    User getByTypeAndId(long typeId, long id);

    @EzyQuery("select e from User e where e.typeId = ?0 and e.status = ?1 and e.id =?2 ")
    User getByTypeAndStatusAndId(long typeId, int status, long id);

    @EzyQuery("select e from User e where e.typeId = ?0 and e.username =?1 ")
    User getByTypeAndUserName(long typeId, String userName);

    @EzyQuery("select e from User e where e.typeId = ?0 and e.status =?1 and e.username =?2")
    User getByTypeAndStatusAndUserName(long typeId, int status, String userName);

}

