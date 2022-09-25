package com.ezyfox.cvconnect.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddMenuUserData {
    private long userId;
    private long menuId;
}
