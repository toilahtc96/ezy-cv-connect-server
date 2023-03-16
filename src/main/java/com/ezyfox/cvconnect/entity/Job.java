package com.ezyfox.cvconnect.entity;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job")
@SuperBuilder
public class Job extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "job_type_id")
    private Long jobTypeId;
    @Column(name = "company_id")
    private Long companyId;
    private Integer quantity;
    @Column(name = "range_salary_min")
    private Double rangeSalaryMin;
    @Column(name = "range_salary_max")
    private Double rangeSalaryMax;
    private String information;
    @Column(name = "level_id")
    private Long levelId;
    @Column(name = "custom_range")
    private Boolean customRange;
    @Column(name = "career_id")
    private Long careerId;
    @Column(name = "working_form_id")
    private Long workingFormId;
    @Enumerated(EnumType.STRING)
    private EntityStatus status;
    @Column(name = "created_id")
    private Long createdId;
}
