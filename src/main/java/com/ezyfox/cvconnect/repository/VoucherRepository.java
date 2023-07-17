package com.ezyfox.cvconnect.repository;

import com.ezyfox.cvconnect.entity.Voucher;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@EzyRepository
public interface VoucherRepository extends EzyDatabaseRepository<Long, Voucher> {

    @EzyQuery(value = "select * from Voucher e where 1 = 1 and " +
            " (?0 is null OR e.voucher_type = ?0  ) and " +
            " (?1 is null OR e.value = ?1 ) and " +
            " (?2 is null OR e.status = ?2 ) and " +
            " (?3 is null OR e.created_id = ?3 ) and " +
            " (?4 is null OR e.start_time <= ?4 ) and " +
            " (?5 is null OR e.end_time >= ?5 ) and " +
            " (?6 is null OR e.title like concat('%',?6,'%') ) " +
            " limit ?7 offset ?8 ", nativeQuery = true)
    List<Voucher> findByField(
            String voucherType,
            String value,
            String status,
            Long createdId,
            LocalDateTime from,
            LocalDateTime to,
            String title,
            int size,
            int skip
    );

    @EzyQuery(value = "select count(*) from Voucher e where 1 = 1 and " +
            " (?0 is null OR e.voucher_type = ?0  ) and " +
            " (?1 is null OR e.value = ?1 ) and " +
            " (?2 is null OR e.status = ?2 ) and " +
            " (?3 is null OR e.created_id = ?3 ) and " +
            " (?4 is null OR e.start_time <= ?4 ) and " +
            " (?5 is null OR e.end_time >= ?5 ) and " +
            " (?6 is null OR e.title like concat('%',?6,'%') ) ", nativeQuery = true)
    BigInteger totalByField(
            String voucherType,
            String value,
            String status,
            Long createdId,
            LocalDateTime from,
            LocalDateTime to,
            String title
    );
}
