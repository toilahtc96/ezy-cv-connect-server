package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.builder.VoucherInfoBuilder;
import com.ezyfox.cvconnect.constant.LevelName;
import com.ezyfox.cvconnect.entity.*;
import com.ezyfox.cvconnect.repository.*;
import com.ezyfox.cvconnect.response.*;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import javax.persistence.Tuple;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private final JobTypeRepository jobTypeRepository;
    private final WorkingFormRepository workingFormRepository;
    private final VoucherRepository voucherRepository;
    private final UserRepository userRepository;


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

    public WorkingFormResponse toResponse(WorkingForm workingForm) {
        return WorkingFormResponse
                .builder()
                .name(workingForm.getName())
                .id(workingForm.getId())
                .description(workingForm.getDescription())
                .status(workingForm.getStatus())
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

    public StepResponse toStepResponse(Step step) {
        return StepResponse
                .builder()
                .id(step.getId())
                .code(step.getCode())
                .meaning(step.getMeaning())
                .status(step.getStatus())
                .build();
    }

    public ProgressResponse toProgressResponse(Progress progress) {
        if (progress == null) {
            return null;
        }
        return ProgressResponse
                .builder()
                .id(progress.getId())
                .agencyId(progress.getAgencyId())
                .candidateId(progress.getCandidateId())
                .stepId(progress.getStepId())
                .status(progress.getStatus())
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
        User reviewOwner = userRepository.findById(review.getReviewOwner());
        String reviewOwnerName = "";
        String reviewOwnerAvatar = "";
        if (review.getReviewOwner() != null) {
            reviewOwnerName = reviewOwner == null
                    ? "" : reviewOwner.getName();
            reviewOwnerAvatar = reviewOwner == null
                    ? "" : reviewOwner.getAvatarUrl();
        }

        return ReviewResponse
                .builder()
                .id(review.getId())
                .description(review.getDescription())
                .star(review.getStar())
                .objectId(review.getObjectId())
                .reviewOwner(review.getReviewOwner())
                .type(review.getType())
                .status(review.getStatus())
                .reviewOwnerName(reviewOwnerName)
                .reviewOwnerAvatar(reviewOwnerAvatar)
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
                .avatarUrl(user.getAvatarUrl())
                .build();
    }

    public AddressResponse toAddressResponse(Address address) {

        Address parent = null;
        if (address.getParentId() != null) {
            parent = addressRepository.findById(address.getParentId());
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

    public List<WorkingFormResponse> toListWorkingFormResponse(List<WorkingForm> workingForms) {
        return workingForms.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public WorkingFormResponse toWorkingFormResponse(WorkingForm workingForm) {
        return WorkingFormResponse
                .builder()
                .id(workingForm.getId())
                .name(workingForm.getName())
                .description(workingForm.getDescription())
                .status(workingForm.getStatus())
                .build();
    }

    public JobResponse toJobResponse(Job job) {
        LevelName levelName = null;
        String careerName = "";
        String jobTypeName = "";
        String companyName = "";
        String workingFormName = "";
        String voucherTitle = "";
        String voucherInfo = "";
        String agencyName = "";
        String avatarUrl = "";
        if (job.getLevelId() != null) {
            Level level = levelRepository.findById(job.getLevelId());
            levelName = level != null ? level.getName() : null;
        }
        if (job.getJobTypeId() != null) {
            JobType jobTypeId = jobTypeRepository.findById(job.getJobTypeId());
            jobTypeName = jobTypeId != null ? jobTypeId.getName() : jobTypeName;
        }
        if (job.getCompanyId() != null) {
            Company companyId = companyRepository.findById(job.getCompanyId());
            companyName = companyId != null ? companyId.getName() : companyName;
        }
        if (job.getWorkingFormId() != null) {
            WorkingForm workingFormById = workingFormRepository.findById(job.getWorkingFormId());
            workingFormName = workingFormById != null ? workingFormById.getName() : workingFormName;
        }
        if (job.getCareerId() != null) {
            Career careerById = careerRepository.findById(job.getCareerId());
            careerName = careerById != null ? careerById.getName() : careerName;
        }
        if (job.getVoucherId() != null) {
            Voucher voucher = voucherRepository.findById(job.getVoucherId());
            voucherTitle = voucher != null ? voucher.getTitle() : voucherTitle;
            voucherInfo = voucher != null
                    ? VoucherInfoBuilder.builder().build().buildByVoucher(voucher) : voucherInfo;
        }
        if (job.getCreatedId() != null) {
            User agency = userRepository.findById(job.getCreatedId());
            agencyName = agency != null ? agency.getName() : agencyName;
            avatarUrl = agency != null ? agency.getAvatarUrl() : avatarUrl;
        }
        return JobResponse
                .builder()
                .id(job.getId()).careerId(job.getCareerId()).careerName(careerName)
                .jobTypeId(job.getJobTypeId()).jobTypeName(jobTypeName).companyId(job.getCompanyId())
                .companyName(companyName).customRange(job.getCustomRange()).createdId(job.getCreatedId())
                .quantity(job.getQuantity()).rangeSalaryMin(job.getRangeSalaryMin())
                .rangeSalaryMax(job.getRangeSalaryMax()).workingFormId(job.getWorkingFormId())
                .workingFormName(workingFormName).levelName(levelName)
                .information(job.getInformation()).status(job.getStatus()).thumbnail(job.getThumbnail())
                .title(job.getTitle()).voucherId(job.getVoucherId()).voucherTitle(voucherTitle)
                .voucherInfo(voucherInfo).agencyName(agencyName).agencyId(job.getCreatedId()).avatarUrl(avatarUrl)
                .tags(job.getTags()).reasonForChoosing(job.getReasonForChoosing())
                .build();
    }

    public VoucherResponse toVoucherResponse(Voucher voucher) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date startDate;
        Date endDate;
        try {
            startDate = dateFormat.parse(voucher.getStartTime().toString());
            endDate = dateFormat.parse(voucher.getEndTime().toString());
        } catch (Exception ex) {
            startDate = null;
            endDate = null;
        }

        return VoucherResponse
                .builder()
                .id(voucher.getId())
                .title(voucher.getTitle())
                .value(voucher.getValue())
                .startTime(Objects.isNull(startDate)
                        ? null : dateFormat.format(startDate))
                .endTime(Objects.isNull(endDate)
                        ? null : dateFormat.format(endDate))
                .voucherType(voucher.getVoucherType())
                .status(voucher.getStatus())
                .build();
    }
}
