package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchUserData {
    private Long typeId;
    private String name;
    private String username;
    private Long companyId;
    private Long levelId;
    private Integer experienceYear;
    private EntityStatus status;
    private Long roleId;
    private String userType;
    private String level;
    private Integer experience;
    private Integer star;
    private int size;
    private int skip;
}
