package com.ezyfox.cvconnect.request;

import lombok.Data;

@Data
public class EditMenuUserRequest {
    private long id;
    private long userId;
    private long menuId;
}
