package com.ezyfox.cvconnect.entity;

import com.ezyfox.cvconnect.constant.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class User extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "company_id")
    private Long companyId;
    @Column(name = "type_id")
    private Integer typeId;
    private String description;
    private Integer star;
    private String information;
    @Column(name = "birth_day")
    private Date birthDay;
    private String name;
    @Column(name = "level_id")
    private Integer levelId;
    @Column(name = "year_experience")
    private Integer yearExperience;
    @Column(name = "cv_link")
    private String cvLink;
    private String username;
    private String password;
    private UserStatus status;
}
