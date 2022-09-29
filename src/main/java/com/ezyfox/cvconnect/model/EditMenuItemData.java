package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.MenuType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EditMenuItemData {
    private long id;
    private String name;
    private Long parentId;
    private EntityStatus status;
    private MenuType type;
    private String path;
    private String pathAddressPhysic;
}
