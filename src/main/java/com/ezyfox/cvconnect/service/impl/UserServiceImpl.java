package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.LevelName;
import com.ezyfox.cvconnect.constant.RoleCode;
import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.constant.UserTypeCode;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.DataToResponseConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.*;
import com.ezyfox.cvconnect.model.*;
import com.ezyfox.cvconnect.repository.LevelRepository;
import com.ezyfox.cvconnect.repository.RoleRepository;
import com.ezyfox.cvconnect.repository.UserRepository;
import com.ezyfox.cvconnect.repository.UserTypeRepository;
import com.ezyfox.cvconnect.response.LoginResponse;
import com.ezyfox.cvconnect.response.UserResponse;
import com.ezyfox.cvconnect.service.AuthenticationService;
import com.ezyfox.cvconnect.service.RoleService;
import com.ezyfox.cvconnect.service.UserService;
import com.ezyfox.cvconnect.validator.UserValidator;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.util.EzyLoggable;
import lombok.AllArgsConstructor;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class UserServiceImpl extends EzyLoggable implements UserService {

    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final UserValidator userValidator;
    private final DataToEntityConverter dataToEntityConverter;
    private final EntityToResponseConverter entityToResponseConverter;
    private final LevelRepository levelRepository;
    private final UserTypeRepository userTypeRepository;
    private final DataToResponseConverter dataToResponseConverter;


    @Override
    public void registerUserAdmin(RegisterData registerData) {
        userValidator.validateUserAdminRegister(registerData);
        User newUser = dataToEntityConverter.dataToUserAdmin(registerData);
        newUser.setStatus(UserStatus.ACTIVED);
        newUser.setName(newUser.getUsername());
        newUser.setRoleId(
                Objects.requireNonNull(
                                roleService.getRoleByCodeActive(RoleCode.USER).stream().findFirst().orElse(null)
                        )
                        .getId()
        );
        userRepository.save(newUser);
    }

    @Override
    public LoginResponse login(LoginData logindata) {
        User userLogin = userValidator.validateUserLogin(logindata);
        UserType userType = null;
        if (userLogin.getTypeId() != null) {
            userType = userTypeRepository.findById(userLogin.getTypeId());
        }

        Role role = null;
        if (userLogin.getRoleId() != null) {
            role = roleRepository.findById(userLogin.getRoleId());
        }

        return dataToResponseConverter.toLoginResponse(
                authenticationService.generateAccessToken(userLogin.getId()),
                role != null ? role.getCode() : null,
                userType != null ? userType.getCode() : null
        );
    }

    public Map<String, Object> getUserPaging(SearchUserData searchUserData) {
        Map<String, Object> mapData = new HashMap<>();

        UserType userTypeByCode = null;
        Level levelByName = null;
        Long userTypeId = -1L;
        if (searchUserData.getUserType() != null) {
            userTypeByCode = userTypeRepository.getUserTypeByCode(UserTypeCode.valueOf(searchUserData.getUserType()));
            if (userTypeByCode != null) {
                userTypeId = userTypeByCode.getId();
            }
        }
        Long levelId = -1L;
        if (searchUserData.getLevel() != null) {
            levelByName = levelRepository.getLevelByName(LevelName.valueOf(searchUserData.getLevel()));
            if (levelByName != null) {
                levelId = levelByName.getId();
            }
        }
        List<UserResponse> listData = userRepository.searchUser(
                searchUserData.getName(),
                searchUserData.getUsername(),
                searchUserData.getUserType() != null ? userTypeId : null,
                searchUserData.getCompanyId(),
                searchUserData.getExperience(),
                searchUserData.getStatus(),
                searchUserData.getLevel() != null ? levelId : null,
                searchUserData.getStar(),
                searchUserData.getSize(),
                searchUserData.getSkip()
        ).stream().map(entityToResponseConverter::toUserResponse).collect(Collectors.toList());

        BigInteger totalElementByField = userRepository.totalSearchUser(
                searchUserData.getName(),
                searchUserData.getUsername(),
                searchUserData.getUserType() != null ? userTypeId : null,
                searchUserData.getCompanyId(),
                searchUserData.getExperience(),
                searchUserData.getStatus(),
                searchUserData.getLevel() != null ? levelId : null,
                searchUserData.getStar()
        );
        mapData.put("data", listData);
        mapData.put("total", totalElementByField);
        return mapData;
    }

    @Override
    public UserResponse getUserById(long id) {
        return entityToResponseConverter.toUserResponse(userRepository.findById(id));
    }

    @Override
    public void editUser(EditUserData editUserData) throws IllegalArgumentException {
        User user = userRepository.findById(editUserData.getId());
        final Level level = levelRepository.findById(editUserData.getLevelId());
        UserType userType = userTypeRepository.getUserTypeByCode(editUserData.getUserTypeCode());
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("Id khong hop le");
        }
        if (editUserData.getStatus() != null) {
            user.setStatus(editUserData.getStatus());
        }
        user.setName(editUserData.getName());
        if (editUserData.getRoleId() != null) {
            user.setRoleId(editUserData.getRoleId());
        }
        if (level != null) {
            user.setLevelId(level.getId());
        }
        if (user.getBirthDay() != null) {
            user.setBirthDay(editUserData.getBirthDay());
        }
        user.setCompanyId(editUserData.getCompanyId());
        if (editUserData.getCvLink() != null) {
            user.setCvLink(editUserData.getCvLink());
        }
        if (editUserData.getTypeId() != null) {
            user.setTypeId(userType != null ? userType.getId() : null);
        }
        if (editUserData.getInformation() != null) {
            user.setInformation(editUserData.getInformation());
        }
        if (editUserData.getDescription() != null) {
            user.setDescription(editUserData.getDescription());
        }
        if (editUserData.getStar() != null) {
            user.setStar(editUserData.getStar());
        }
        if (editUserData.getExperienceYear() != null) {
            user.setExperienceYear(editUserData.getExperienceYear());
        }
        user.setAvatarUrl(editUserData.getAvatarUrl());
        userRepository.save(user);
    }

    @Override
    public UserResponse getByToken(String accessToken) {
        long userId = authenticationService.verifyAccessToken(accessToken);
        User user = userRepository.findById(userId);

        return entityToResponseConverter.toUserResponse(user);
    }

    @Override
    public void registerUser(UserRegisterData userRegisterData) {
        userValidator.validateUserRegister(userRegisterData);
        User newUser = dataToEntityConverter.dataToUser(userRegisterData);
        newUser.setStatus(UserStatus.ACTIVED);
        newUser.setName(newUser.getUsername());
        newUser.setRoleId(
                Objects.requireNonNull(
                                roleService.getRoleByCodeActive(RoleCode.USER).stream().findFirst().orElse(null)
                        )
                        .getId()
        );
        userRepository.save(newUser);
    }
}
