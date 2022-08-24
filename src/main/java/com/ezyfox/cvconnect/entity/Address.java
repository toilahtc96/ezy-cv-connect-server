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
@Table(name = "address")
@SuperBuilder
public class Address extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int type;
    private String code;
    private String name;
    @Column(name = "parent_id")
    private long parentId;
    @Column(name = "created_id")
    private long createdId;
    private EntityStatus status;
}
