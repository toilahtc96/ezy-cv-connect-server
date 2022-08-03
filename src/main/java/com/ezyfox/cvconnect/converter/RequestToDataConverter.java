package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.model.*;
import com.ezyfox.cvconnect.request.*;
import com.ezyfox.cvconnect.util.DateUtil;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

import java.text.ParseException;
import java.util.Date;

@EzySingleton
public class RequestToDataConverter {

    public LoginData toData(LoginRequest loginRequest) {
        return
            LoginData
                .builder()
                .username(loginRequest.getUsername())
                .password(loginRequest.getPassword())
                .build();
    }

    public RegisterData toData(RegisterRequest registerRequest) {
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

    public AddAddressData toData(AddAddressRequest addAddressRequest) {
        return AddAddressData
            .builder()
            .parentId(addAddressRequest.getParentId())
            .type(addAddressRequest.getType())
            .name(addAddressRequest.getName())
            .build();
    }

    public AddressData toData(EditAddressRequest editAddressRequest) {
        return AddressData
            .builder()
            .id(editAddressRequest.getId())
            .name(editAddressRequest.getName())
            .parentId(editAddressRequest.getParentId())
            .type(editAddressRequest.getType())
            .build();
    }

    public AddRoleData toData(AddRoleRequest addRoleRequest) {
        return AddRoleData
            .builder()
            .code(addRoleRequest.getCode())
            .name(addRoleRequest.getName())
            .build();
    }

    public RoleData toData(RoleRequest editRoleRequest) {
        return RoleData
            .builder()
            .id(editRoleRequest.getId())
            .name(editRoleRequest.getName())
            .code(editRoleRequest.getCode())
            .status(editRoleRequest.getStatus())
            .build();
    }

    public AddUserTypeData toData(AddUserTypeRequest addUserTypeRequest) {
        return AddUserTypeData
            .builder()
            .code(addUserTypeRequest.getCode())
            .meaning(addUserTypeRequest.getMeaning())
            .build();
    }

    public EditUserTypeData toData(EditUserTypeRequest editUserTypeRequest) {
        return EditUserTypeData
            .builder()
            .id(editUserTypeRequest.getId())
            .code(editUserTypeRequest.getCode())
            .meaning(editUserTypeRequest.getMeaning())
            .status(editUserTypeRequest.getStatus())
            .build();
    }
}
