package com.ezyfox.cvconnect.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "access_token")
@EqualsAndHashCode(of = "accessToken", callSuper = false)
public class AccessToken extends CommonEntity {

    @Id
    @Column(name = "access_token")
    private String accessToken;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "expire_at")
    private LocalDateTime expireAt;
    @Column(name = "expire_in")
    private LocalDateTime expireIn;
    @Column(name = "first_issue_at")
    private LocalDateTime firstIssueAt;
    private String deleted;
}
