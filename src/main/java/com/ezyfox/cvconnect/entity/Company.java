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
@Table(name = "company")
@SuperBuilder
public class Company extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code;
    private String name;
    @Column(name = "province_code")
    private String provinceCode;
    @Column(name = "district_code")
    private String districtCode;
    @Column(name = "precinct_code")
    private String precinctCode;
    private String information;
    private int star;
    private EntityStatus status;
}
