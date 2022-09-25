package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.request.AddMenuUserRequest;
import com.ezyfox.cvconnect.request.EditMenuUserRequest;
import com.ezyfox.cvconnect.response.MenuUserResponse;
import com.ezyfox.cvconnect.service.MenuUserService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

@Controller("api/v1/menu-user")
@AllArgsConstructor
@Authenticated
public class MenuUserController {

    private final MenuUserService menuUserService;
    private final RequestToDataConverter requestToDataConverter;


    @DoPost("/add")
    public ResponseEntity addMenuUser(@RequestBody AddMenuUserRequest addMenuItemRequest) {
        menuUserService.addMenuUser(requestToDataConverter.toDataFromAddMenuUser(addMenuItemRequest));
        return ResponseEntity.noContent();
    }

    @DoPost("/edit")
    public ResponseEntity editMenuUser(@RequestBody EditMenuUserRequest editMenuUserRequest) {
        menuUserService.editMenuUser(requestToDataConverter.toDataFromEditMenuUser(editMenuUserRequest));
        return ResponseEntity.noContent();
    }

    @DoGet("/get-by-user-id/{userId}")
    public List<MenuUserResponse> getByUserId(@PathVariable long userId) {
        return menuUserService.getByUserId(userId);
    }

    @DoGet("/get-by-menu-id/{userId}")
    public List<MenuUserResponse> getByMenuId(@PathVariable long menuId) {
        return menuUserService.getByMenuId(menuId);
    }

    @DoGet("/get-by-user-id-and-menu-id")
    public List<MenuUserResponse> getByUserIdAndMenuId(@RequestParam long userId, @RequestParam long menuId) {
        return menuUserService.getByMenuIdAndUserId(menuId, userId);
    }
}
