package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.request.AddMenuItemRequest;
import com.ezyfox.cvconnect.request.EditMenuItemRequest;
import com.ezyfox.cvconnect.response.MenuItemResponse;
import com.ezyfox.cvconnect.service.MenuItemService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

@Controller("api/v1/menu")
@AllArgsConstructor
@Authenticated
public class MenuItemController {

    private final MenuItemService menuItemService;
    private final RequestToDataConverter requestToDataConverter;

    @DoGet("/user/{userId}")
    public List<MenuItemResponse> getMenuOfUser(@PathVariable long userId) {
        return menuItemService.getByUserId(userId);
    }

    @DoPost("/add")
    public ResponseEntity addMenuItem(@RequestBody AddMenuItemRequest addMenuItemRequest) {
        menuItemService.addMenuItem(requestToDataConverter.toDataFromAddMenuItem(addMenuItemRequest));
        return ResponseEntity.noContent();
    }

    @DoPost("/edit")
    public ResponseEntity editMenuItem(@RequestBody EditMenuItemRequest editMenuItemRequest) {
        menuItemService.editMenuItem(requestToDataConverter.toDataFromEditMenuItem(editMenuItemRequest));
        return ResponseEntity.noContent();
    }
}
