package com.ezyfox.cvconnect.request;

import lombok.Data;

@Data
public class AddMenuUserRequest {
    private long userId;
    private long menuId;
}
