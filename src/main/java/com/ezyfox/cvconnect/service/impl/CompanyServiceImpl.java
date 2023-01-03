package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.entity.Company;
import com.ezyfox.cvconnect.exception.ResourceNotFoundException;
import com.ezyfox.cvconnect.model.AddCompanyData;
import com.ezyfox.cvconnect.model.EditCompanyData;
import com.ezyfox.cvconnect.repository.CompanyRepository;
import com.ezyfox.cvconnect.response.CompanyResponse;
import com.ezyfox.cvconnect.service.CompanyServie;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyServie {

    private final CompanyRepository companyRepository;
    private final RequestToDataConverter requestToDataConverter;
    private final DataToEntityConverter dataToEntityConverter;
    private final EntityToResponseConverter entityToResponseConverter;

    @Override
    public void addCompany(AddCompanyData addCompanyData) {
        companyRepository.save(dataToEntityConverter.dataToCompany(addCompanyData));
    }

    @Override
    public void editCompany(EditCompanyData editCompanyData) {
        Company companyFromId = companyRepository.findById(editCompanyData.getId());
        if (companyFromId == null) {
            throw new ResourceNotFoundException("Company");
        }
        companyFromId.setCode(editCompanyData.getCode());
        companyFromId.setName(editCompanyData.getName());
        companyFromId.setProvinceCode(editCompanyData.getProvinceCode());
        companyFromId.setDistrictCode(editCompanyData.getDistrictCode());
        companyFromId.setPrecinctCode(editCompanyData.getPrecinctCode());
        companyFromId.setStar(editCompanyData.getStar());
        companyFromId.setStatus(editCompanyData.getStatus().name());
        companyFromId.setInformation(editCompanyData.getInformation());
        companyRepository.save(companyFromId);
    }

    @Override
    public List<CompanyResponse> getByProvinceCode(String provinceCode) {
        return companyRepository
            .getByProvinceCode(provinceCode)
            .stream()
            .map(entityToResponseConverter::toCompanyResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<CompanyResponse> getByDistrictCode(String districtCode) {
        return companyRepository
            .getByDistrictCode(districtCode)
            .stream()
            .map(entityToResponseConverter::toCompanyResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<CompanyResponse> getByPrecinctCode(String precinctCode) {
        return companyRepository
            .getByPrecinctCode(precinctCode)
            .stream()
            .map(entityToResponseConverter::toCompanyResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<CompanyResponse> getByStar(int star) {
        return companyRepository
            .getByStar(star)
            .stream()
            .map(entityToResponseConverter::toCompanyResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<CompanyResponse> getByStatus(EntityStatus status) {
        return companyRepository
            .getByStatus(status)
            .stream()
            .map(entityToResponseConverter::toCompanyResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<CompanyResponse> getByName(String name) {
        return companyRepository
            .getByName(name)
            .stream()
            .map(entityToResponseConverter::toCompanyResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<CompanyResponse> getByCode(String code) {
        return companyRepository
            .getByCode(code)
            .stream()
            .map(entityToResponseConverter::toCompanyResponse)
            .collect(Collectors.toList());
    }

    @Override
    public CompanyResponse getById(long companyId) {
        return entityToResponseConverter.toCompanyResponse(companyRepository.findById(companyId));
    }

    @Override
    public List<CompanyResponse> getAll() {
        return companyRepository
            .findAll()
            .stream()
            .map(entityToResponseConverter::toCompanyResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<CompanyResponse> getPaging(int page, int size) {
        int skip = page*size;
        return companyRepository
            .findAll(skip,size)
            .stream()
            .map(entityToResponseConverter::toCompanyResponse)
            .collect(Collectors.toList());
    }
}
