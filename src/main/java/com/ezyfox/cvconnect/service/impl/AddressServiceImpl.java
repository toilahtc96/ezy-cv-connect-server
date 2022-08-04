package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.AddressType;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.Address;
import com.ezyfox.cvconnect.model.AddAddressData;
import com.ezyfox.cvconnect.model.AddressData;
import com.ezyfox.cvconnect.repository.AddressRepository;
import com.ezyfox.cvconnect.response.AddressResponse;
import com.ezyfox.cvconnect.service.AddressService;
import com.ezyfox.cvconnect.util.AddressUtil;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final DataToEntityConverter dataToEntityConverter;
    private final EntityToResponseConverter entityToResponseConverter;

    @Override
    public void saveAddress(AddAddressData data) {
        Address newAddress = dataToEntityConverter.dataToAddress(data);
        String firstLetterName = data.getName().substring(0, 1);
        long countOfAddressByNameAndType = addressRepository
            .getCountAddressByNameStartAndType(firstLetterName, data.getType());
        Address parentAddress = addressRepository.findById(data.getParentId());
        newAddress.setCode(
            AddressUtil.buildCodeOfAddress(
                AddressType.of(data.getType()),
                data.getName(),
                countOfAddressByNameAndType,
                parentAddress)
        );
        addressRepository.save(newAddress);
    }

    @Override
    public void editAddress(AddressData data) {
        Address addressById = addressRepository.findById(data.getId());
        if (addressById == null) {
            throw new HttpBadRequestException("Address By Id Not Found");
        }
        if (addressById.getStatus() != 1) {
            throw new HttpBadRequestException("Address By Id Not Active");
        }
        if (data.getType() != 0) {
            addressById.setType(data.getType());
        }
        if (data.getName() != null) {
            addressById.setName(data.getName());
        }
        if (data.getParentId() != 0) {
            addressById.setParentId(data.getParentId());
        }
        String firstLetterName = data.getName().substring(0, 1);
        long countOfAddressByNameAndType = addressRepository
            .getCountAddressByNameStartAndType(firstLetterName, data.getType());
        Address parentAddress = addressRepository.findById(data.getParentId());
        addressById.setCode(
            AddressUtil.buildCodeOfAddress(
                AddressType.of(addressById.getType()),
                addressById.getName(),
                countOfAddressByNameAndType,
                parentAddress
            )
        );
        addressById.setUpdatedTime(LocalDateTime.now());
        addressRepository.save(addressById);
    }


    @Override
    public List<AddressResponse> getAddressByType(int type) {
        List<Address> listByType = addressRepository.findAllByType(type);
        if (listByType != null && listByType.size() > 0) {
            return listByType
                .stream()
                .map(entityToResponseConverter::toResponse)
                .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<AddressResponse> getAddressByParentId(long parentId) {
        List<Address> listByParentId = addressRepository.findAllByParentId(parentId);
        if (listByParentId != null && listByParentId.size() > 0) {
            return listByParentId.stream().map(entityToResponseConverter::toResponse).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<AddressResponse> getAddressByTypeAndParentId(int type, long parentId) {
        List<Address> listByTypeAndParentId = addressRepository.findAllByTypeAndParentId(type, parentId);
        if (listByTypeAndParentId != null && listByTypeAndParentId.size() > 0) {
            return listByTypeAndParentId
                .stream()
                .map(entityToResponseConverter::toResponse)
                .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
