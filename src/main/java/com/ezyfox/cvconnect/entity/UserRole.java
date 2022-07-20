package com.ezyfox.cvconnect.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_role")
@EqualsAndHashCode(of = "user_role", callSuper = false)
public class UserRole extends CommonEntity {
    @Id
    private long id;
    @Column(name ="user_id")
    private long userId;
    @Column(name ="role_id")
    private long roleId;
}
