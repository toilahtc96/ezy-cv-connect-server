package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.request.AddCompanyRequest;
import com.ezyfox.cvconnect.request.EditCompanyRequest;
import com.ezyfox.cvconnect.response.CompanyResponse;
import com.ezyfox.cvconnect.service.CompanyServie;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

@Controller("api/v1/company")
@AllArgsConstructor
@Authenticated
public class CompanyController {

  private final CompanyServie companyServie;
  private final RequestToDataConverter requestToDataConverter;

  @DoPost("/add")
  public ResponseEntity addCompany(@RequestBody AddCompanyRequest addCompanyRequest) {
    companyServie.addCompany(requestToDataConverter.toDataFromAddCompany(addCompanyRequest));
    return ResponseEntity.noContent();
  }

  @DoPost("/edit")
  public ResponseEntity editCompany(@RequestBody EditCompanyRequest editCompanyRequest) {
    companyServie.editCompany(requestToDataConverter.toDataFromEditCompany(editCompanyRequest));
    return ResponseEntity.noContent();
  }

  @DoGet("/get-by-province-code")
  public List<CompanyResponse> getByProvinceCode(@RequestParam String provinceCode) {
    return companyServie.getByProvinceCode(provinceCode);
  }

  @DoGet("/get-by-district-code")
  public List<CompanyResponse> getByDistrictCode(@RequestParam String districtCode) {
    return companyServie.getByDistrictCode(districtCode);
  }

  @DoGet("/get-by-precinct-code")
  public List<CompanyResponse> getByPrecinctCode(@RequestParam String precinctCode) {
    return companyServie.getByPrecinctCode(precinctCode);
  }

  @DoGet("/get-by-code")
  public List<CompanyResponse> getByCode(@RequestParam String code) {
    return companyServie.getByCode(code);
  }

  @DoGet("/get-by-name")
  public List<CompanyResponse> getByName(@RequestParam String name) {
    return companyServie.getByName(name);
  }

  @DoGet("/get-by-status")
  public List<CompanyResponse> getByStatus(@RequestParam EntityStatus status) {
    return companyServie.getByStatus(status);
  }

  @DoGet("/get-by-star")
  public List<CompanyResponse> getByStar(@RequestParam int star) {
    return companyServie.getByStar(star);
  }

  @DoGet("/get-by-id")
  public CompanyResponse getById(@RequestParam long id) {
    return companyServie.getById(id);
  }

  @DoGet("/get-page")
  public List<CompanyResponse> getPage(@RequestParam int page, @RequestParam int size) {
    return companyServie.getPaging(page, size);
  }

  @DoGet("/get-all")
  public List<CompanyResponse> getAll() {
    return companyServie.getAll();
  }
}
