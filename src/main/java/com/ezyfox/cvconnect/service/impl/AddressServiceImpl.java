package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.builder.AddressCodeBuilder;
import com.ezyfox.cvconnect.constant.AddressType;
import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.Address;
import com.ezyfox.cvconnect.exception.ResourceNotFoundException;
import com.ezyfox.cvconnect.model.AddAddressData;
import com.ezyfox.cvconnect.model.AddressData;
import com.ezyfox.cvconnect.model.SearchAddressData;
import com.ezyfox.cvconnect.repository.AddressRepository;
import com.ezyfox.cvconnect.response.AddressResponse;
import com.ezyfox.cvconnect.service.AddressService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final DataToEntityConverter dataToEntityConverter;
    private final EntityToResponseConverter entityToResponseConverter;

    @Override
    public void saveAddress(AddAddressData data) throws IllegalAccessException {
        Address newAddress = dataToEntityConverter.dataToAddress(data);
        String firstLetterName = data.getName().substring(0, 1);
        long countOfAddressByNameAndType = addressRepository
                .getCountAddressByNameStartAndType("%" + firstLetterName + "%", data.getType());
        Address parentAddress = addressRepository.findById(data.getParentId());
        AddressCodeBuilder addressCodeBuilder = AddressCodeBuilder.builder()
                .name(data.getName())
                .type(data.getType())
                .parentAddress(parentAddress)
                .countOfAddressByNameAndType(countOfAddressByNameAndType)
                .build();
        newAddress.setCode(
                addressCodeBuilder.build()
        );
        Address checkAddressExistedByName = addressRepository
                .findByNameAndType(newAddress.getName(), newAddress.getType());
        if (checkAddressExistedByName != null
                && checkAddressExistedByName.getParentId() == parentAddress.getId()) {
            throw new IllegalAccessException("Đã tồn tại Địa chỉ với thông tin này");
        }
        addressRepository.save(newAddress);
    }

    @Override
    public void editAddress(AddressData data) {
        Address addressById = addressRepository.findById(data.getId());
        if (addressById == null) {
            throw new ResourceNotFoundException("Address");
        }
        if (!addressById.getStatus().equals(EntityStatus.ACTIVED)) {
            throw new ResourceNotFoundException("Address Active");
        }
        if (data.getType() != null) {
            addressById.setType(data.getType());
        }
        if (data.getName() != null) {
            addressById.setName(data.getName());
        }
        if (data.getParentId() != 0) {
            addressById.setParentId(data.getParentId());
        }
        addressById.setStatus(data.getStatus());
        String firstLetterName = data.getName().substring(0, 1);
        long countOfAddressByNameAndType = addressRepository
                .getCountAddressByNameStartAndType(firstLetterName, data.getType());
        Address parentAddress = addressRepository.findById(data.getParentId());
        AddressCodeBuilder addressCodeBuilder = AddressCodeBuilder.builder()
                .name(data.getName())
                .type(data.getType())
                .parentAddress(parentAddress)
                .countOfAddressByNameAndType(countOfAddressByNameAndType)
                .build();
        addressById.setCode(
                addressCodeBuilder.build()
        );
        addressById.setUpdatedTime(LocalDateTime.now());
        addressRepository.save(addressById);
    }


    @Override
    public List<AddressResponse> getAddressByType(AddressType type) {
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
    public List<AddressResponse> getAddressByTypeAndParentId(AddressType type, long parentId) {
        List<Address> listByTypeAndParentId = addressRepository.findAllByTypeAndParentId(type, parentId);
        if (listByTypeAndParentId != null && listByTypeAndParentId.size() > 0) {
            return listByTypeAndParentId
                    .stream()
                    .map(entityToResponseConverter::toResponse)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<AddressResponse> getAll() {
        return addressRepository
                .findAll()
                .stream()
                .map(entityToResponseConverter::toResponse)
                .sorted(Comparator.comparing(AddressResponse::getParentId))
                .collect(Collectors.toList());
    }

    @Override
    public AddressResponse getById(long id) {
        return entityToResponseConverter.toResponse(addressRepository.findById(id));
    }

    @Override
    public List<AddressResponse> getByParentCode(String code) {
        Address parentAddress = addressRepository.findByField("code", code);
        if (parentAddress == null) {
            return new ArrayList<>();
        } else {
            return addressRepository
                    .findAllByParentId(parentAddress.getId())
                    .stream()
                    .map(entityToResponseConverter::toResponse)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public AddressResponse getByCodeAndType(String code, AddressType type) {
        return addressRepository.findByCodeAndType(code, type) == null
                ? null : entityToResponseConverter.toResponse(addressRepository.findByCodeAndType(code, type));
    }

    @Override
    public List<AddressResponse> getAddressByTypePaging(AddressType type, int offset, int size) {
        List<Address> listByType = addressRepository.findAllByTypePaging(AddressType.PROVINCE.getName(), offset, size);
        if (listByType != null && listByType.size() > 0) {
            return listByType
                    .stream()
                    .map(entityToResponseConverter::toResponse)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public Map<String, Object> getByField(SearchAddressData searchAddressData) {
        int skip = searchAddressData.getPage() * searchAddressData.getSize();
        Map<String, Object> mapData = new HashMap<>();
        List<AddressResponse> listData = addressRepository.searchAddress(
                        searchAddressData.getName(),
                        searchAddressData.getStatus() != null ? searchAddressData.getStatus().toString() : null,
                        searchAddressData.getSize(),
                        skip
                )
                .stream()
                .map(entityToResponseConverter::toAddressResponse)
                .collect(Collectors.toList());
        BigInteger totalElementByField = addressRepository.totalSearchAddress(
                searchAddressData.getName(),
                searchAddressData.getStatus() != null ? searchAddressData.getStatus().toString() : null
        );
        mapData.put("data", listData);
        mapData.put("total", totalElementByField);
        return mapData;
    }
}
