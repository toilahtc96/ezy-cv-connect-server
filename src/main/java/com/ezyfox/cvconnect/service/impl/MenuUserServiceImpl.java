package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.entity.MenuUser;
import com.ezyfox.cvconnect.exception.ResourceNotFoundException;
import com.ezyfox.cvconnect.model.AddMenuUserData;
import com.ezyfox.cvconnect.model.EditMenuUserData;
import com.ezyfox.cvconnect.repository.MenuUserRepository;
import com.ezyfox.cvconnect.response.MenuUserResponse;
import com.ezyfox.cvconnect.service.MenuUserService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class MenuUserServiceImpl implements MenuUserService {

    private final MenuUserRepository menuUserRepository;
    private final DataToEntityConverter dataToEntityConverter;


    @Override
    public List<MenuUserResponse> getAll() {
        return null;
    }

    @Override
    public List<MenuUserResponse> getByUserId(long userId) {
        return menuUserRepository
            .getMenuUserByUserId(userId)
            .stream()
            .map(menuUser -> MenuUserResponse
                .builder()
                .id(menuUser.getId())
                .menuId(menuUser.getMenuId())
                .userId(menuUser.getUserId())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public List<MenuUserResponse> getByMenuId(long menuId) {
        return menuUserRepository.getMenuUserByUserId(menuId).stream()
            .map(menuUser -> MenuUserResponse
                .builder()
                .id(menuUser.getId())
                .menuId(menuUser.getMenuId())
                .userId(menuUser.getUserId())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public List<MenuUserResponse> getByMenuIdAndUserId(long menuId, long userId) {
        return menuUserRepository.getMenuUserByMenuIdAndUserId(menuId, userId).stream()
            .map(menuUser -> MenuUserResponse
                .builder()
                .id(menuUser.getId())
                .menuId(menuUser.getMenuId())
                .userId(menuUser.getUserId())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public void addMenuUser(AddMenuUserData addMenuUserData) {
        menuUserRepository.save(dataToEntityConverter.dataToMenuUser(addMenuUserData));
    }

    @Override
    public void editMenuUser(EditMenuUserData editMenuUserData) {
        MenuUser menuUserById = menuUserRepository.findById(editMenuUserData.getId());
        if (menuUserById == null) {
            throw new ResourceNotFoundException("Menu User");
        }
        menuUserById.setMenuId(editMenuUserData.getMenuId());
        menuUserById.setUserId(editMenuUserData.getUserId());
        menuUserById.setUpdatedTime(LocalDateTime.now());
        menuUserRepository.save(menuUserById);
    }
}
