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
@Table(name = "address")
@EqualsAndHashCode(of = "address", callSuper = false)
public class Address extends CommonEntity {

    @Id
    private long id;
    private long type;
    private String code;
    private String name;
}
