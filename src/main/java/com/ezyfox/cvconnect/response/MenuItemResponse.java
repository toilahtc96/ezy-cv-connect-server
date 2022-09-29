package com.ezyfox.cvconnect.response;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.MenuType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuItemResponse {
    private String name;
    private long parentId;
    private MenuType type;
    private String path;
    private EntityStatus status;
    private String pathAddressPhysic;
}
