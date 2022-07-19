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
@Table(name = "company")
@EqualsAndHashCode(of = "company", callSuper = false)
public class Company extends CommonEntity {
    @Id
    private long id;
    private String code;
    private String name;
    private String provinceCode;
    private String districtCode;
    private String precinctCode;
    private String information;
    private long star;
}
