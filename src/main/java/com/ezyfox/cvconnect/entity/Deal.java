package com.ezyfox.cvconnect.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deal")
@EqualsAndHashCode(of = "deal", callSuper = false)
public class Deal extends CommonEntity {

    @Id
    private long id;
    private long agencyId;
    private long candidateId;
    private long status;
    private long processId;
}
