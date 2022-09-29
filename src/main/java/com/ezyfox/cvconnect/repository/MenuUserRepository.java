package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.entity.MenuUser;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.util.List;

@EzyRepository
public interface MenuUserRepository extends EzyDatabaseRepository<String, MenuUser> {

    @EzyQuery("select e.menuId from MenuUser e where e.userId = ?0")
    List<Long> getMenuIdByUserId(long userId);

    @EzyQuery("select e from MenuUser e where e.userId = ?0")
    List<MenuUser> getMenuUserByUserId(long userId);

    @EzyQuery("select e from MenuUser e where e.menuId = ?0")
    List<MenuUser> getMenuUserByMenuId(long menuId);

    @EzyQuery("select e from MenuUser e where e.menuId = ?0 and e.userId = ?1")
    List<MenuUser> getMenuUserByMenuIdAndUserId(long menuId, long userId);

    @EzyQuery("select e from MenuUser e where e.id = ?0")
    MenuUser findById(long id);
}
