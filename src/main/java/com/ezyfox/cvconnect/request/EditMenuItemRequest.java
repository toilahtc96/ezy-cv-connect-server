package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.MenuType;
import lombok.Data;

@Data
public class EditMenuItemRequest {
    private long id;
    private String name;
    private long parentId;
    private EntityStatus status;
    private MenuType type;
    private String path;
    private String pathAddressPhysic;
}
