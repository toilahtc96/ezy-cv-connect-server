package com.ezyfox.cvconnect.entity;

import com.ezyfox.cvconnect.constant.UserStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user")
public class User extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "role_id")
    private long roleId;
    @Column(name = "company_id")
    private long companyId;
    @Column(name = "type_id")
    private int typeId;
    private String description;
    private int star;
    private String information;
    @Column(name = "birth_day")
    private Date birthDay;
    private String name;
    @Column(name = "level_id")
    private int levelId;
    @Column(name = "year_experience")
    private int yearExperience;
    @Column(name = "cv_link")
    private String cvLink;
    private String username;
    private String password;
    private UserStatus status;
}
