package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.model.*;
import com.ezyfox.cvconnect.response.CompanyResponse;
import com.ezyfox.cvconnect.response.JobResponse;

import java.util.List;
import java.util.Map;

public interface JobService {

    void addJob(AddJobData addJobData, Long userId);

    void editJob(EditJobData editJobData);
//
//    List<CompanyResponse> getByProvinceCode(String provinceCode);
//
//    List<CompanyResponse> getByDistrictCode(String districtCode);
//
//    List<CompanyResponse> getByPrecinctCode(String precinctCode);
//
//    List<CompanyResponse> getByStar(int star);
//
//    List<CompanyResponse> getByStatus(EntityStatus status);
//
//    List<CompanyResponse> getByName(String name);
//
//    List<CompanyResponse> getByCode(String code);
//
    JobResponse getById(long id);
//
//    List<CompanyResponse> getAll();
//
//    List<CompanyResponse> getPaging(int page, int size);
//
    Map<String, Object> getByField(SearchJobData searchJobData);
}
