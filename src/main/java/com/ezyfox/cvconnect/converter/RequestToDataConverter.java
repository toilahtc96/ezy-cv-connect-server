package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.model.*;
import com.ezyfox.cvconnect.request.*;
import com.ezyfox.cvconnect.util.DateUtil;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

import java.text.ParseException;
import java.util.Date;

@EzySingleton
public class RequestToDataConverter {

    public LoginData toLoginData(LoginRequest loginRequest) {
        return
            LoginData
                .builder()
                .username(loginRequest.getUsername())
                .password(loginRequest.getPassword())
                .build();
    }

    public RegisterData toRegisterData(RegisterRequest registerRequest) {
        try {
            return RegisterData
                .builder()
                .birthDay(DateUtil.parseFromStringFormat(
                    registerRequest.getBirthDay(),
                    DateUtil.DATE_DDMMYYYY_PATTERN
                ))
                .name(registerRequest.getName())
                .cvLink(registerRequest.getCvLink())
                .levelId(registerRequest.getLevelId())
                .status(registerRequest.getStatus())
                .typeId(registerRequest.getTypeId())
                .password(registerRequest.getPassword())
                .yearExperience(registerRequest.getYearExperience())
                .username(registerRequest.getUsername())
                .build();
        } catch (ParseException parseException) {
            return null;
        }

    }

    public AddAddressData toAddAddressData(AddAddressRequest addAddressRequest) {
        return AddAddressData
            .builder()
            .parentId(addAddressRequest.getParentId())
            .type(addAddressRequest.getType())
            .name(addAddressRequest.getName())
            .build();
    }

    public AddressData toAddressDataFromEdit(EditAddressRequest editAddressRequest) {
        return AddressData
            .builder()
            .id(editAddressRequest.getId())
            .name(editAddressRequest.getName())
            .parentId(editAddressRequest.getParentId())
            .type(editAddressRequest.getType())
            .build();
    }

    public AddRoleData toAddRoleData(AddRoleRequest addRoleRequest) {
        return AddRoleData
            .builder()
            .code(addRoleRequest.getCode())
            .name(addRoleRequest.getName())
            .build();
    }

    public RoleData toRoleDataFromEdit(RoleRequest editRoleRequest) {
        return RoleData
            .builder()
            .id(editRoleRequest.getId())
            .name(editRoleRequest.getName())
            .code(editRoleRequest.getCode())
            .status(editRoleRequest.getStatus())
            .build();
    }
}
