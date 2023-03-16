package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.converter.CareerRequestToDataConverter;
import com.ezyfox.cvconnect.model.AddCareerData;
import com.ezyfox.cvconnect.model.CareerData;
import com.ezyfox.cvconnect.request.AddCareerRequest;
import com.ezyfox.cvconnect.request.CareerRequest;
import com.ezyfox.cvconnect.response.CareerResponse;
import com.ezyfox.cvconnect.service.CareerService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

@Controller("api/v1/career")
@AllArgsConstructor
@Authenticated
public class CareerController {

    private final CareerService careerService;
    private final CareerRequestToDataConverter careerRequestToDataConverter;

    @DoPost("/add")
    public ResponseEntity addCareer(@RequestBody AddCareerRequest addCareerRequest) {
        AddCareerData addCareerData = careerRequestToDataConverter.toDataFromAddCareer(addCareerRequest);
        careerService.addCareer(addCareerData);
        return ResponseEntity.noContent();
    }

    @DoPost("/edit")
    public ResponseEntity editCareer(@RequestBody CareerRequest editCareerRequest) {
        CareerData careerData = careerRequestToDataConverter.toDataFromEditCareer(editCareerRequest);
        careerService.editCareer(careerData);
        return ResponseEntity.noContent();
    }

    @DoGet("/get-by-id")
    public CareerResponse getById(@RequestParam long id) {
        return careerService.getById(id);
    }

    @DoGet("/get-by-code")
    public List<CareerResponse> getByCode(@RequestParam String code) {
        return careerService.getCareerByCodeActive(code);
    }

    @DoGet("/get-by-name")
    public List<CareerResponse> getByName(@RequestParam String name) {
        return careerService.getCareerByNameActive(name);
    }

    @DoGet("/get-all-active")
    public List<CareerResponse> getAllActive() {
        return careerService.getAllCareerActive();
    }

    @DoGet("/get-all")
    public List<CareerResponse> getAll() {
        return careerService.getAllCareer();
    }

    @DoGet("/get-page")
    public List<CareerResponse> getPage(@RequestParam int page, @RequestParam int size) {
        return careerService.getPaging(page, size);
    }
}
