package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.AddMenuItemData;
import com.ezyfox.cvconnect.model.EditMenuItemData;
import com.ezyfox.cvconnect.response.MenuItemResponse;

import java.util.List;

public interface MenuItemService {

    List<MenuItemResponse> getMenuOfUser();

    List<MenuItemResponse> getAll();

    void addMenuItem(AddMenuItemData addMenuItemData);

    void editMenuItem(EditMenuItemData editMenuItemData);

}
