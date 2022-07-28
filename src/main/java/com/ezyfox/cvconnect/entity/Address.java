package com.ezyfox.cvconnect.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
@Builder
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
}
