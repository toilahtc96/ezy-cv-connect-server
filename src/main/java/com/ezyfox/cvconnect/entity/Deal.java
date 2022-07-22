package com.ezyfox.cvconnect.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deal")
public class Deal extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "agency_id")
    private long agencyId;
    @Column(name = "candidate_id")
    private long candidateId;
    private long status;
    @Column(name = "process_id")
    private long processId;
}
