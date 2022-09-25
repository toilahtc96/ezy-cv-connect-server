package com.ezyfox.cvconnect.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuUserResponse {
    private long id;
    private long menuId;
    private long userId;
}
