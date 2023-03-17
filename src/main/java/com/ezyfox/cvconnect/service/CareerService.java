package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.AddCareerData;
import com.ezyfox.cvconnect.model.CareerData;
import com.ezyfox.cvconnect.model.SearchCareerData;
import com.ezyfox.cvconnect.model.SearchJobTypeData;
import com.ezyfox.cvconnect.response.CareerResponse;

import java.util.List;
import java.util.Map;

public interface CareerService {

    void addCareer(AddCareerData careerData);

    void editCareer(CareerData careerData);

    List<CareerResponse> getCareerByCodeActive(String code);

    List<CareerResponse> getCareerByNameActive(String name);

    List<CareerResponse> getAllCareerActive();

    List<CareerResponse> getAllCareer();

    List<CareerResponse> getPaging(int page, int size);

    CareerResponse getById(long id);

    Map<String, Object> getByField(SearchCareerData searchCareerData);
}
