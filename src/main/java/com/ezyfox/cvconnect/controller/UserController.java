package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.entity.UserType;
import com.ezyfox.cvconnect.model.SearchUserData;
import com.ezyfox.cvconnect.response.UserResponse;
import com.ezyfox.cvconnect.service.UserService;
import com.sun.istack.Nullable;
import com.tvd12.ezyhttp.server.core.annotation.Authenticated;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;
import lombok.AllArgsConstructor;

import java.util.Map;

@Controller("api/v1/user")
@AllArgsConstructor
@Authenticated
public class UserController {

    private final UserService userService;
    private final RequestToDataConverter requestToDataConverter;


    @DoGet("/get-page")
    public Map<String, Object> getPage(@RequestParam("page") int page, @RequestParam("size") int size,
                                       @Nullable @RequestParam("typeId") Long typeId, @Nullable @RequestParam("name") String name) {
        int skip = size * page;
        SearchUserData searchUserData = SearchUserData.builder().size(size).skip(skip).name(name).typeId(typeId).build();
        return userService.getUserPaging(searchUserData);
    }

    @DoGet("/get-by-id")
    public UserResponse getUserById(@RequestParam("id") long id) {
        return userService.getUserById(id);
    }

}
