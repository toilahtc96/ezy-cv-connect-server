package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.AddMenuUserData;
import com.ezyfox.cvconnect.model.EditMenuUserData;
import com.ezyfox.cvconnect.response.MenuUserResponse;

import java.util.List;

public interface MenuUserService {

    List<MenuUserResponse> getByUserId(long userId);

    List<MenuUserResponse> getByMenuId(long menuId);

    List<MenuUserResponse> getByMenuIdAndUserId(long menuId, long userId);

    List<MenuUserResponse> getAll();

    void addMenuUser(AddMenuUserData addMenuUserData);

    void editMenuUser(EditMenuUserData editMenuUserData);

}
