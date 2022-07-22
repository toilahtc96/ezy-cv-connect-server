package com.ezyfox.cvconnect.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
public class Review extends CommonEntity {

    @Id
    private long id;
    private String description;
    private long star;
    @Column(name = "object_id")
    private long objectId;
    @Column(name = "review_owner")
    private long reviewOwner;
    private long type;
}
