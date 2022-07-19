package com.ezyfox.cvconnect.entity;

import java.util.Date;
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
@Table(name = "user")
@EqualsAndHashCode(of = "user", callSuper = false)
public class User extends CommonEntity {

    @Id
    private long id;
    private long roleId;
    private long companyId;
    private long typeId;
    private String description;
    private long star;
    private String information;
    private Date birthDay;
    private String name;
    private long levelId;
    private long yearExperience;
    private String cvLink;
    private String username;
    private String password;
    private long status;
}
