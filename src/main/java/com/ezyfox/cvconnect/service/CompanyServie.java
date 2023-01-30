package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.model.AddCompanyData;
import com.ezyfox.cvconnect.model.EditCompanyData;
import com.ezyfox.cvconnect.model.SearchCompanyData;
import com.ezyfox.cvconnect.response.CompanyResponse;

import java.util.List;

public interface CompanyServie {

    void addCompany(AddCompanyData addCompanyData);

    void editCompany(EditCompanyData editCompanyData);

    List<CompanyResponse> getByProvinceCode(String provinceCode);

    List<CompanyResponse> getByDistrictCode(String districtCode);

    List<CompanyResponse> getByPrecinctCode(String precinctCode);

    List<CompanyResponse> getByStar(int star);

    List<CompanyResponse> getByStatus(EntityStatus status);

    List<CompanyResponse> getByName(String name);

    List<CompanyResponse> getByCode(String code);

    CompanyResponse getById(long companyId);

    List<CompanyResponse> getAll();

    List<CompanyResponse> getPaging(int page, int size);

    List<CompanyResponse> getByField(SearchCompanyData searchCompanyData);
}
