package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.MenuType;
import lombok.Data;

@Data
public class AddMenuItemRequest {
    private String name;
    private Long parentId;
    private EntityStatus status;
    private MenuType type;
    private String path;
    private String pathAddressPhysic;
}
