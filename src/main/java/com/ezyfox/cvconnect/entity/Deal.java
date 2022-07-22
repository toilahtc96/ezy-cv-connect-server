package com.ezyfox.cvconnect.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deal")
public class Deal extends CommonEntity {

    @Id
    private long id;
    @Column(name = "agency_id")
    private long agencyId;
    @Column(name = "candidate_id")
    private long candidateId;
    private long status;
    @Column(name = "process_id")
    private long processId;
}
