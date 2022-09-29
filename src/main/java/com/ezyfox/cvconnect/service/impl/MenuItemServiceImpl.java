package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.entity.MenuItem;
import com.ezyfox.cvconnect.exception.ResourceNotFoundException;
import com.ezyfox.cvconnect.model.AddMenuItemData;
import com.ezyfox.cvconnect.model.EditMenuItemData;
import com.ezyfox.cvconnect.repository.MenuItemRepository;
import com.ezyfox.cvconnect.response.MenuItemResponse;
import com.ezyfox.cvconnect.service.MenuItemService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;
import org.eclipse.jetty.util.StringUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final DataToEntityConverter dataToEntityConverter;

    @Override
    public List<MenuItemResponse> getByUserId(long userId) {
        return menuItemRepository
            .getByIdIn(userId, EntityStatus.ACTIVE)
            .stream()
            .map(
                menuItem ->
                    MenuItemResponse
                        .builder()
                        .name(menuItem.getName())
                        .parentId(menuItem.getParentId())
                        .path(menuItem.getPath())
                        .pathAddressPhysic(menuItem.getPathAddressPhysic())
                        .type(menuItem.getType())
                        .build()
            )
            .collect(Collectors.toList());
    }

    @Override
    public List<MenuItemResponse> getAll() {
        return null;
    }

    @Override
    public void addMenuItem(AddMenuItemData addMenuItemData) {
        menuItemRepository.save(dataToEntityConverter.dataToMenuItem(addMenuItemData));
    }

    @Override
    public void editMenuItem(EditMenuItemData editMenuItemData) {
        MenuItem menuItemById = menuItemRepository.getById(editMenuItemData.getId());
        if (menuItemById == null) {
            throw new ResourceNotFoundException("Menu Item");
        }
        if (!StringUtil.isBlank(editMenuItemData.getName())) {
            menuItemById.setName(editMenuItemData.getName());
        }
        if (!StringUtil.isBlank(editMenuItemData.getPath())) {
            menuItemById.setPath(editMenuItemData.getPath());
        }
        if (!StringUtil.isBlank(editMenuItemData.getPathAddressPhysic())) {
            menuItemById.setPathAddressPhysic(editMenuItemData.getPathAddressPhysic());
        }
        if (editMenuItemData.getParentId() != null) {
            menuItemById.setParentId(editMenuItemData.getParentId());
        }
        menuItemById.setType(editMenuItemData.getType());
        menuItemById.setUpdatedTime(LocalDateTime.now());
        if (editMenuItemData.getStatus() != null) {
            menuItemById.setStatus(editMenuItemData.getStatus());
        }
        menuItemRepository.save(menuItemById);
    }
}
