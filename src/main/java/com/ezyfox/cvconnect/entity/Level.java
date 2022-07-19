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
@Table(name = "level")
@EqualsAndHashCode(of = "level", callSuper = false)
public class Level extends CommonEntity {

    @Id
    private long id;
    private String name;
    private String meaning;
}
