package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.entity.MenuItem;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.util.List;

@EzyRepository
public interface MenuItemRepository extends EzyDatabaseRepository<String, MenuItem> {

    @EzyQuery("select e from MenuItem e where e.id in ?0 and e.status = ?1")
    List<MenuItem> getByIdIn(long menuId, EntityStatus status);

    @EzyQuery("select e from MenuItem e where e.id = ?0")
    MenuItem getById(long id);
}
