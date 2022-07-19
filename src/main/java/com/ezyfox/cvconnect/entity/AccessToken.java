package com.ezyfox.cvconnect.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="access_token")
@EqualsAndHashCode(of = "accessToken", callSuper = false)
public class AccessToken extends CommonEntity {

    @Id
    private String accessToken;
    private Long userId;
    private LocalDateTime expireAt;
    private LocalDateTime expireIn;
    private LocalDateTime firstIssueAt;
    private String deleted;
}
