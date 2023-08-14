package com.ezyfox.cvconnect.service.impl;

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
    public void saveAddress(AddAddressData data) {
        Address newAddress = dataToEntityConverter.dataToAddress(data);
        setValueToSave(
                newAddress,
                data.getType(),
                data.getProvinceCode(),
                data.getProvinceName(),
                data.getDistrictCode(),
                data.getDistrictName(),
                data.getPrecinctCode(),
                data.getPrecinctName(),
                data.getStatus()
        );
    }

    private void setValueToSave(
            Address newAddress,
            AddressType type,
            String provinceCode,
            String provinceName,
            String districtCode,
            String districtName,
            String precinctCode,
            String precinctName,
            EntityStatus status
    ) {
        if (type != null) {
            newAddress.setType(type);
        }
        if (provinceCode != null) {
            newAddress.setProvinceCode(provinceCode);
        }
        if (provinceName != null) {
            newAddress.setProvinceName(provinceName);
        }
        if (districtCode != null) {
            newAddress.setDistrictCode(districtCode);
        }
        if (districtName != null) {
            newAddress.setDistrictName(districtName);
        }
        if (precinctCode != null) {
            newAddress.setPrecinctCode(precinctCode);
        }
        if (precinctName != null) {
            newAddress.setPrecinctName(precinctName);
        }
        newAddress.setStatus(status);
        newAddress.setUpdatedTime(LocalDateTime.now());
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
        setValueToSave(
                addressById,
                data.getType(),
                data.getProvinceCode(),
                data.getProvinceName(),
                data.getDistrictCode(),
                data.getDistrictName(),
                data.getPrecinctCode(),
                data.getPrecinctName(),
                data.getStatus()
        );
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
    public List<AddressResponse> getAll() {
        return addressRepository
                .findAll()
                .stream()
                .map(entityToResponseConverter::toResponse)
                .sorted(Comparator.comparing(AddressResponse::getProvinceName))
                .collect(Collectors.toList());
    }

    @Override
    public AddressResponse getById(long id) {
        return entityToResponseConverter.toResponse(addressRepository.findById(id));
    }


    @Override
    public AddressResponse getByCodeAndType(String code, AddressType type) {
        return addressRepository.findByCodeAndType(code, type == null ? null : type) == null
                ? null : entityToResponseConverter.toResponse(
                addressRepository.findByCodeAndType(
                        code,
                        type));
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
                searchAddressData.getProvinceName(),
                searchAddressData.getProvinceCode(),
                searchAddressData.getDistrictName(),
                searchAddressData.getDistrictCode(),
                searchAddressData.getPrecinctName(),
                searchAddressData.getPrecinctCode(),
                searchAddressData.getType() != null
                        ? searchAddressData.getType() == null
                        ? null : searchAddressData.getType().getName() : null,
                searchAddressData.getStatus() != null
                        ? searchAddressData.getStatus() == null
                        ? null : searchAddressData.getStatus().toString() : null,
                searchAddressData.getSize(),
                skip
        )
                .stream()
                .map(entityToResponseConverter::toAddressResponse)
                .collect(Collectors.toList());
        BigInteger totalElementByField = addressRepository.totalSearchAddress(
                searchAddressData.getProvinceName(),
                searchAddressData.getProvinceCode(),
                searchAddressData.getDistrictName(),
                searchAddressData.getDistrictCode(),
                searchAddressData.getPrecinctName(),
                searchAddressData.getPrecinctCode(),
                searchAddressData.getType() != null
                        ? searchAddressData.getType() == null
                        ? null : searchAddressData.getType().getName() : null,
                searchAddressData.getStatus() != null
                        ? searchAddressData.getStatus() == null
                        ? null : searchAddressData.getStatus().toString() : null
        );
        mapData.put("data", listData);
        mapData.put("total", totalElementByField);
        return mapData;
    }

    @Override
    public List<AddressResponse> getByTypeAndParentCode(String type, String parentCode) {
        return addressRepository.findAllByTypeAndParentCode(type, parentCode).stream()
                .map(entityToResponseConverter::toAddressResponse)
                .collect(Collectors.toList());
    }
}
