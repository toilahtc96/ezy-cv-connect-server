package com.ezyfox.cvconnect.entity;

import com.ezyfox.cvconnect.constant.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "user")
public class User extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "company_id")
    private Long companyId;
    @Column(name = "type_id")
    private Long typeId;
    private String description;
    private Integer star;
    private String information;
    @Column(name = "birth_day")
    private Date birthDay;
    private String name;
    @Column(name = "level_id")
    private Integer levelId;
    @Column(name = "experience_year")
    private Integer experienceYear;
    @Column(name = "cv_link")
    private String cvLink;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
