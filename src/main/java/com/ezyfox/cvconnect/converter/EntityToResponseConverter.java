package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.entity.Process;
import com.ezyfox.cvconnect.entity.*;
import com.ezyfox.cvconnect.response.*;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

import javax.persistence.Tuple;
import java.util.List;
import java.util.stream.Collectors;

@EzySingleton
public class EntityToResponseConverter {

    public AddressResponse toResponse(Address address) {
        return AddressResponse
            .builder()
            .type(address.getType())
            .parentId(address.getParentId())
            .name(address.getName())
            .id(address.getId())
            .code(address.getCode())
            .name(address.getName())
            .status(address.getStatus())
            .build();
    }

    public RoleResponse toResponse(Role role) {
        return RoleResponse
            .builder()
            .name(role.getName())
            .id(role.getId())
            .code(role.getCode())
            .status(role.getStatus())
            .build();
    }

    public List<RoleResponse> toListRoleResponse(List<Role> roles) {
        return roles.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public UserTypeResponse toUserTypeResponse(UserType userType) {
        return UserTypeResponse
            .builder()
            .id(userType.getId())
            .code(userType.getCode().getName())
            .meaning(userType.getMeaning())
            .build();
    }

    public List<UserTypeResponse> toListUserTypeResponse(List<UserType> userTypes) {
        return userTypes.stream().map(this::toUserTypeResponse).collect(Collectors.toList());
    }

    public UserRoleResponse toUserRoleResponse(UserRole userRole) {
        return UserRoleResponse
            .builder()
            .userRoleId(userRole.getId())
            .roleId(userRole.getRoleId())
            .userId(userRole.getUserId())
            .status(userRole.getStatus())
            .build();
    }

    public ProcessResponse toProcessResponse(Process process) {
        return ProcessResponse
            .builder()
            .processId(process.getId())
            .code(process.getCode())
            .meaning(process.getMeaning())
            .status(process.getStatus())
            .build();
    }

    public DealResponse toDealResponse(Deal deal) {
        return DealResponse
            .builder()
            .dealId(deal.getId())
            .agencyId(deal.getAgencyId())
            .candidateId(deal.getCandidateId())
            .processId(deal.getProcessId())
            .status(deal.getStatus())
            .build();
    }

    public LevelResponse toLevelResponse(Level level) {
        return LevelResponse
            .builder()
            .id(level.getId())
            .levelName(level.getName())
            .meaning(level.getMeaning())
            .status(level.getStatus())
            .build();
    }

    public ReviewResponse toReviewResponse(Review review) {
        return ReviewResponse
            .builder()
            .id(review.getId())
            .description(review.getDescription())
            .star(review.getStar())
            .objectId(review.getObjectId())
            .reviewOwner(review.getReviewOwner())
            .type(review.getType())
            .status(review.getStatus())
            .build();
    }

    public CompanyResponse toCompanyResponse(Company company) {
        return CompanyResponse
            .builder()
            .id(company.getId())
            .code(company.getCode())
            .name(company.getName())
            .provinceCode(company.getProvinceCode())
            .districtCode(company.getDistrictCode())
            .precinctCode(company.getPrecinctCode())
            .information(company.getInformation())
            .star(company.getStar())
            .status(company.getStatus())
            .build();
    }

    public CompanyResponse toCompanyResponse(Tuple company) {
        return CompanyResponse
                .builder()
                .id(Long.parseLong((String) company.get("id")))
                .build();
    }

    public MenuItemResponse toMenuItemResponse(MenuItem menuItem) {
        return MenuItemResponse
            .builder()
            .name(menuItem.getName())
            .parentId(menuItem.getParentId())
            .status(menuItem.getStatus())
            .path(menuItem.getPath())
            .pathAddressPhysic(menuItem.getPathAddressPhysic())
            .build();
    }

    public MenuUserResponse toMenuUserResponse(MenuUser menuUser) {
        return MenuUserResponse
            .builder()
            .id(menuUser.getId())
            .menuId(menuUser.getMenuId())
            .userId(menuUser.getUserId())
            .build();
    }

    public RoleResponse toRoleResponse(Role role) {
        return RoleResponse
                .builder()
                .id(role.getId())
                .code(role.getCode())
                .name(role.getName())
                .status(role.getStatus())
                .build();
    }

    public UserResponse toUserResponse(User user) {
        return UserResponse
                .builder()
                .roleId(user.getRoleId())
                .status(user.getStatus())
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }
}
