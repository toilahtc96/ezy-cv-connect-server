package com.ezyfox.cvconnect.entity;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "progress")
@SuperBuilder
public class Progress extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "agency_id")
    private long agencyId;
    @Column(name = "job_id")
    private long jobId;
    @Column(name = "candidate_id")
    private long candidateId;
    @Enumerated(EnumType.STRING)
    private EntityStatus status;
    @Column(name = "step_id")
    private Long stepId;
}