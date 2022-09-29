package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.LevelName;
import com.ezyfox.cvconnect.entity.Level;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.util.List;

@EzyRepository
public interface LevelRepository extends EzyDatabaseRepository<Long, Level> {
    @EzyQuery("select e from Level e where e.name = ?0")
    List<Level> getLevelByName(LevelName name);

    @EzyQuery("select e from Level e where e.status = ?0")
    List<Level> getLevelByStatus(EntityStatus name);
}
