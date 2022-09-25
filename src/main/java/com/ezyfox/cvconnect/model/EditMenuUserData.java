package com.ezyfox.cvconnect.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EditMenuUserData {
    private long id;
    private long userId;
    private long menuId;
}
