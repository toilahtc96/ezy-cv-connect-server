package com.ezyfox.cvconnect.entity;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.VoucherType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "voucher")
@SuperBuilder
public class Voucher {

    @Id
    private long id;
    private String title;
    @Column(name = "voucher_type")
    @Enumerated(EnumType.STRING)
    private VoucherType voucherType;
    private double value;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    private EntityStatus status;
    @Column(name = "created_id")
    private Long createdId;
    @Column(name = "created_time")
    private LocalDateTime createdTime;
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
}
