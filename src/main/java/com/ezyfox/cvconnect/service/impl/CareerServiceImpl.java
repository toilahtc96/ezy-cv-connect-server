package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.Career;
import com.ezyfox.cvconnect.exception.ResourceNotFoundException;
import com.ezyfox.cvconnect.model.AddCareerData;
import com.ezyfox.cvconnect.model.CareerData;
import com.ezyfox.cvconnect.repository.CareerRepository;
import com.ezyfox.cvconnect.response.CareerResponse;
import com.ezyfox.cvconnect.service.CareerService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class CareerServiceImpl implements CareerService {

    private final CareerRepository careerRepository;
    private final DataToEntityConverter dataToEntityConverter;
    private final EntityToResponseConverter entityToResponseConverter;


    @Override
    public void addCareer(AddCareerData careerData) {
        Career career = dataToEntityConverter.dataToCareer(careerData);
        careerRepository.save(career);
    }

    @Override
    public void editCareer(CareerData careerData) {
        Career careerById = careerRepository.findById(careerData.getId());
        if (careerById == null) {
            throw new ResourceNotFoundException("Career By Id");
        }
        if (careerData.getName() != null) {
            careerById.setName(careerData.getName());
        }
        careerById.setStatus(careerData.getStatus());
        careerById.setUpdatedTime(LocalDateTime.now());
        careerRepository.save(careerById);
    }

    @Override
    public List<CareerResponse> getCareerByCodeActive(String code) {
        return entityToResponseConverter.toListCareerResponse(
                careerRepository.getCareerByCodeAndStatus(code, EntityStatus.ACTIVED)
        );
    }

    @Override
    public List<CareerResponse> getCareerByNameActive(String name) {
        return entityToResponseConverter.toListCareerResponse(
                careerRepository.getCareerByNameAndStatus(name, EntityStatus.ACTIVED)
        );
    }

    @Override
    public List<CareerResponse> getAllCareerActive() {
        return entityToResponseConverter.toListCareerResponse(
                careerRepository.getAllCareerByStatus(EntityStatus.ACTIVED)
        );
    }

    @Override
    public List<CareerResponse> getAllCareer() {
        return entityToResponseConverter.toListCareerResponse(careerRepository.getAllCareer());
    }

    @Override
    public List<CareerResponse> getPaging(int page, int size) {
        int skip = page * size;
        return careerRepository
                .findAll(skip, size)
                .stream()
                .map(entityToResponseConverter::toCareerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CareerResponse getById(long id) {
        return entityToResponseConverter.toCareerResponse(careerRepository.findById(id));
    }
}
