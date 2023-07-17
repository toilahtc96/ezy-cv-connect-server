package com.ezyfox.cvconnect.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public class CommonEntity {
    @Column(name = "created_time")
    private LocalDateTime createdTime;
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
}
