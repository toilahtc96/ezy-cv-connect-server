package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchUserData {
    private Long typeId;
    private String username;
    private Long companyId;
    private Long levelId;
    private Integer experienceYear;
    private EntityStatus status;
    private Long roleId;
    private int size;
    private int skip;
}
