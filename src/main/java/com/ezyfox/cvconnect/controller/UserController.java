package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.model.SearchUserData;
import com.ezyfox.cvconnect.response.UserResponse;
import com.ezyfox.cvconnect.service.UserService;
import com.tvd12.ezyhttp.server.core.annotation.Authenticated;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;
import lombok.AllArgsConstructor;

import java.util.List;

@Controller("api/v1/user")
@AllArgsConstructor
@Authenticated
public class UserController {

    private final UserService userService;
    private final RequestToDataConverter requestToDataConverter;


    @DoGet("/get-page")
    public List<UserResponse> getPage(@RequestParam int page, @RequestParam int size) {
        int skip = size * page;
        SearchUserData searchUserData = SearchUserData.builder().size(size).skip(skip).build();
        return userService.getUserPaging(searchUserData);
    }

}
