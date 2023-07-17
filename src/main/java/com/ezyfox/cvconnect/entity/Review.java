package com.ezyfox.cvconnect.entity;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.ReviewType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
@SuperBuilder
public class Review extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private int star;
    @Column(name = "object_id")
    private Long objectId;
    @Column(name = "review_owner")
    private Long reviewOwner;
    @Enumerated(EnumType.ORDINAL)
    private ReviewType type;
    @Enumerated(EnumType.STRING)
    private EntityStatus status;
    @Column(name = "created_id")
    private Long createdId;
}
