package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.LevelName;
import com.ezyfox.cvconnect.entity.Level;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.math.BigInteger;
import java.util.List;

@EzyRepository
public interface LevelRepository extends EzyDatabaseRepository<Long, Level> {
    @EzyQuery("select e from Level e where e.name = ?0")
    List<Level> getLevelByName(LevelName name);

    @EzyQuery("select e from Level e where e.name = ?0 and e.status = ?1")
    List<Level> getLevelByNameAndStatus(LevelName name, EntityStatus entityStatus);

    @EzyQuery("select e from Level e where e.status = ?0")
    List<Level> getLevelByStatus(EntityStatus name);

    @EzyQuery(value = "select * from Level e where 1 = 1 and " +
        " (?0 is null OR e.meaning like concat('%',?0,'%')  )  and" +
        " (?1 is null OR e.name = ?1 ) and  " +
        " (?2 is null OR e.status = ?2 ) " +
        " limit ?3 offset ?4 ", nativeQuery = true)
    List<Level> searchLevel(String meaning, LevelName name, EntityStatus status, int size, int skip);

    @EzyQuery(value = "select count(*) from Level e where 1 = 1 and " +
        " (?0 is null OR e.meaning like concat('%',?0,'%')  )  and" +
        " (?1 is null OR e.name = ?1 ) and  " +
        " (?2 is null OR e.status = ?2 ) ", nativeQuery = true)
    BigInteger totalSearchLevel(String meaning, LevelName name, EntityStatus status);
}
