package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.Deal;
import com.ezyfox.cvconnect.exception.ResourceNotFoundException;
import com.ezyfox.cvconnect.model.AddDealData;
import com.ezyfox.cvconnect.model.EditDealData;
import com.ezyfox.cvconnect.model.SearchDealData;
import com.ezyfox.cvconnect.repository.DealRepository;
import com.ezyfox.cvconnect.response.DealResponse;
import com.ezyfox.cvconnect.service.DealService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final EntityToResponseConverter entityToResponseConverter;
    private final DataToEntityConverter dataToEntityConverter;

    @Override
    public void addDeal(AddDealData addDealData) {
        dealRepository.save(dataToEntityConverter.dataToDeal(addDealData));
    }

    @Override
    public void editDeal(EditDealData editDealData) {
        Deal dealById = dealRepository.findById(editDealData.getId());
        if (dealById == null) {
            throw new ResourceNotFoundException("Deal By Id");
        }
        dealById.setAgencyId(editDealData.getAgencyId());
        dealById.setCandidateId(editDealData.getCandidateId());
        dealById.setProcessId(editDealData.getProcessId());
        dealById.setStatus(editDealData.getStatus());
        dealById.setUpdatedTime(LocalDateTime.now());
        dealRepository.save(dealById);
    }

    @Override
    public List<DealResponse> getByAgencyId(long agencyId) {
        return dealRepository
                .getByAgencyId(agencyId)
                .stream()
                .map(entityToResponseConverter::toDealResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DealResponse> getByCandidateId(long candidateId) {
        return dealRepository
                .getByCandidateId(candidateId)
                .stream()
                .map(entityToResponseConverter::toDealResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DealResponse> getAll() {
        return dealRepository
                .findAll()
                .stream()
                .map(entityToResponseConverter::toDealResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DealResponse> getAllActiveOfAgency(long agencyId) {
        return dealRepository
                .getActiveDealByAgencyId(agencyId, EntityStatus.ACTIVED)
                .stream()
                .map(entityToResponseConverter::toDealResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getDealPaging(SearchDealData searchDealData) {
        Map<String, Object> data = new HashMap<>();
        List<DealResponse> listData = dealRepository
                .searchDeal(
                        searchDealData.getAgencyId(),
                        searchDealData.getCandidateId(),
                        searchDealData.getProcessId(),
                        searchDealData.getStatus(),
                        searchDealData.getSize(),
                        searchDealData.getSkip()
                )
                .stream()
                .map(entityToResponseConverter::toDealResponse)
                .collect(Collectors.toList());
        BigInteger total = dealRepository.totalSearchDeal(
                searchDealData.getAgencyId(),
                searchDealData.getCandidateId(),
                searchDealData.getProcessId(),
                searchDealData.getStatus());
        data.put("data", listData);
        data.put("total", total);
        return data;
    }

    @Override
    public DealResponse getById(long id) {
        return entityToResponseConverter.toDealResponse(dealRepository.findById(id));
    }
}
