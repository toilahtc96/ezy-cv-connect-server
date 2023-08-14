package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.LevelName;
import com.ezyfox.cvconnect.constant.UserTypeCode;
import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.request.EditUserRequest;
import com.ezyfox.cvconnect.response.UserResponse;
import com.ezyfox.cvconnect.service.UserService;
import com.sun.istack.Nullable;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.Map;

@Controller("api/v1/user")
@AllArgsConstructor
@Authenticated
public class UserController {

    private final UserService userService;
    private final RequestToDataConverter requestToDataConverter;


    @DoGet("/get-page")
    public Map<String, Object> getPage(@RequestParam("page") int page,
                                       @RequestParam("size") int size,
                                       @Nullable @RequestParam("typeId") Long typeId,
                                       @Nullable @RequestParam("name") String name,
                                       @Nullable @RequestParam("username") String username,
                                       @Nullable @RequestParam("companyId") Long companyId,
                                       @Nullable @RequestParam("userType") UserTypeCode userType,
                                       @Nullable @RequestParam("level") LevelName level,
                                       @Nullable @RequestParam("experience") Integer experience,
                                       @Nullable @RequestParam("star") Integer star,
                                       @Nullable @RequestParam("status") EntityStatus status) {
        return userService.getUserPaging(
                requestToDataConverter
                        .toDataFromSearchUser(page, size, typeId, name, username, companyId,
                                userType, level, experience, star, status)
        );
    }

    @DoGet("/get-by-id")
    public UserResponse getUserById(@RequestParam("id") long id) {
        return userService.getUserById(id);
    }

    @DoPost("/edit")
    public ResponseEntity editUser(@RequestBody EditUserRequest editUserRequest) {
        userService.editUser(requestToDataConverter.toDataFromEditUser(editUserRequest));
        return ResponseEntity.noContent();
    }

    @DoGet("/get-user-by-token")
    public UserResponse getByToken(@RequestParam("accessToken") String accessToken) {
        return userService.getByToken(accessToken);
    }
}
