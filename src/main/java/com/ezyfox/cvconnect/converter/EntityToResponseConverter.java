package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.entity.Process;
import com.ezyfox.cvconnect.entity.*;
import com.ezyfox.cvconnect.repository.*;
import com.ezyfox.cvconnect.response.*;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import javax.persistence.Tuple;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class EntityToResponseConverter {

    private final RoleRepository roleRepository;
    private final CompanyRepository companyRepository;
    private final UserTypeRepository userTypeRepository;
    private final LevelRepository levelRepository;
    private final AddressRepository addressRepository;

    private final CareerRepository careerRepository;

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

    public CareerResponse toResponse(Career career) {
        return CareerResponse
                .builder()
                .name(career.getName())
                .id(career.getId())
                .status(career.getStatus())
                .build();
    }

    public JobTypeResponse toResponse(JobType jobType) {
        return JobTypeResponse
                .builder()
                .name(jobType.getName())
                .id(jobType.getId())
                .description(jobType.getDescription())
                .status(jobType.getStatus())
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
                .status(userType.getStatus())
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
                .id(process.getId())
                .code(process.getCode())
                .meaning(process.getMeaning())
                .status(process.getStatus())
                .build();
    }

    public DealResponse toDealResponse(Deal deal) {
        if (deal == null) {
            return null;
        }
        return DealResponse
                .builder()
                .id(deal.getId())
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
        Role roleOfUser = null;
        if (user.getRoleId() != null) {
            roleOfUser = roleRepository.findById(user.getRoleId());
        }
        Company companyOfUser = null;
        if (user.getCompanyId() != null) {
            companyOfUser = companyRepository.findById(user.getCompanyId());
        }
        UserType userType = null;
        if (user.getTypeId() != null) {
            userType = userTypeRepository.findById(user.getTypeId());
        }
        Level level = null;
        if (user.getLevelId() != null) {
            level = levelRepository.findById(user.getLevelId());
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return UserResponse
                .builder()
                .id(user.getId())
                .roleName(roleOfUser == null ? null : roleOfUser.getCode())
                .status(user.getStatus())
                .name(user.getName())
                .username(user.getUsername())
                .birthDay(Objects.isNull(user.getBirthDay()) ? null : dateFormat.format(user.getBirthDay()))
                .companyName(companyOfUser == null ? "" : companyOfUser.getName())
                .companyId(user.getCompanyId())
                .cvLink(user.getCvLink())
                .description(user.getDescription())
                .experienceYear(user.getExperienceYear())
                .information(user.getInformation())
                .levelId(user.getLevelId())
                .level(level != null ? level.getName() : null)
                .typeId(user.getTypeId())
                .userTypeCode(userType != null ? userType.getCode() : null)
                .star(user.getStar())
                .build();
    }

    public AddressResponse toAddressResponse(Address address) {

        Address parent = null;
        if (address.getParentId() != null) {
            parent =  addressRepository.findById(address.getParentId());
        }
        return AddressResponse
                .builder()
                .id(address.getId())
                .code(address.getCode())
                .name(address.getName())
                .type(address.getType())
                .status(address.getStatus())
                .parentId(address.getParentId())
                .parentName(parent != null ? parent.getName() : "")
                .build();
    }


    public List<CareerResponse> toListCareerResponse(List<Career> careers) {
        return careers.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public CareerResponse toCareerResponse(Career career) {
        return CareerResponse
            .builder()
            .id(career.getId())
            .name(career.getName())
            .status(career.getStatus())
            .build();
    }

    public List<JobTypeResponse> toListJobTypeResponse(List<JobType> jobTypes) {
        return jobTypes.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public JobTypeResponse toJobTypeResponse(JobType jobType) {
        return JobTypeResponse
                .builder()
                .id(jobType.getId())
                .name(jobType.getName())
                .description(jobType.getDescription())
                .status(jobType.getStatus())
                .build();
    }
}
