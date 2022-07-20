package com.ezyfox.cvconnect.entity;

import java.util.Date;

import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.request.RegisterRequest;
import com.tvd12.ezyfox.sercurity.EzySHA256;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
@EqualsAndHashCode(of = "user", callSuper = false)
public class User extends CommonEntity {

    @Id
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
    private Integer status;

    public User of(RegisterRequest registerRequest) {
        return User.builder()
                .birthDay(registerRequest.getBirthDay())
                .name(registerRequest.getName())
                .username(registerRequest.getUsername())
                .password(EzySHA256.cryptUtfToLowercase(registerRequest.getPassword()))
                .typeId(registerRequest.getTypeId())
                .status(UserStatus.ACTIVE.getStatus())
                .build();
    }
}
