package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.RoleCode;
import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.User;
import com.ezyfox.cvconnect.model.LoginData;
import com.ezyfox.cvconnect.model.RegisterData;
import com.ezyfox.cvconnect.model.SearchUserData;
import com.ezyfox.cvconnect.repository.UserRepository;
import com.ezyfox.cvconnect.response.UserResponse;
import com.ezyfox.cvconnect.service.AuthenticationService;
import com.ezyfox.cvconnect.service.RoleService;
import com.ezyfox.cvconnect.service.UserService;
import com.ezyfox.cvconnect.validator.UserValidator;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.util.EzyLoggable;
import lombok.AllArgsConstructor;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class UserServiceImpl extends EzyLoggable implements UserService {

    private final RoleService roleService;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final UserValidator userValidator;
    private final DataToEntityConverter dataToEntityConverter;
    private final EntityToResponseConverter entityToResponseConverter;


    @Override
    public void registerUser(RegisterData registerData) {
        userValidator.validateUserRegister(registerData);
        User newUser = dataToEntityConverter.dataToUser(registerData);
        newUser.setStatus(UserStatus.ACTIVED);
        newUser.setName(newUser.getUsername());
        newUser.setRoleId(Objects.requireNonNull(roleService.getRoleByCodeActive(RoleCode.USER).stream().findFirst().orElse(null)).getId());
        userRepository.save(newUser);
    }

    @Override
    public String login(LoginData logindata) {
        User userLogin = userValidator.validateUserLogin(logindata);
        return authenticationService.generateAccessToken(userLogin.getId());
    }

    public Map<String, Object> getUserPaging(SearchUserData searchUserData) {
        Map<String, Object> mapData = new HashMap<>();
        List<UserResponse> listData = userRepository.searchUser(
                searchUserData.getName(),
                searchUserData.getUsername(),
                searchUserData.getTypeId(),
                searchUserData.getCompanyId(),
                searchUserData.getExperienceYear(),
                searchUserData.getStatus(),
                searchUserData.getLevelId(),
                searchUserData.getSize(),
                searchUserData.getSkip()
        ).stream().map(entityToResponseConverter::toUserResponse).collect(Collectors.toList());

        BigInteger totalElementByField = userRepository.totalSearchUser(
                searchUserData.getName(),
                searchUserData.getUsername(),
                searchUserData.getTypeId(),
                searchUserData.getCompanyId(),
                searchUserData.getExperienceYear(),
                searchUserData.getStatus(),
                searchUserData.getLevelId()
        );
        mapData.put("data", listData);
        mapData.put("total", totalElementByField);
        return mapData;
    }

    @Override
    public UserResponse getUserById(long id) {
        return entityToResponseConverter.toUserResponse(userRepository.findById(id));
    }
}
