package com.ezyfox.cvconnect.entity;

import com.ezyfox.cvconnect.constant.AddressType;
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
@Table(name = "address")
@SuperBuilder
public class Address extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private AddressType type;
    @Column(name = "province_code")
    private String provinceCode;
    @Column(name = "province_name")
    private String provinceName;
    @Column(name = "district_code")
    private String districtCode;
    @Column(name = "district_name")
    private String districtName;
    @Column(name = "precinct_code")
    private String precinctCode;
    @Column(name = "precinct_name")
    private String precinctName;
    @Column(name = "created_id")
    private long createdId;
    @Enumerated(EnumType.STRING)
    private EntityStatus status;
}
