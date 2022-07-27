package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.DataToResponseConverter;
import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.entity.Address;
import com.ezyfox.cvconnect.model.AddAddressData;
import com.ezyfox.cvconnect.model.AddressData;
import com.ezyfox.cvconnect.repository.AddressRepository;
import com.ezyfox.cvconnect.service.AddressService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@EzySingleton
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final DataToEntityConverter dataToEntityConverter;
    private final RequestToDataConverter requestToDataConverter;
    private final DataToResponseConverter dataToResponseConverter;

    @Override
    public AddressData saveAddress(AddAddressData data) {
        Address newAddress = dataToEntityConverter.toAddressEntityFromAddData(data);
        newAddress.setCreatedTime(LocalDateTime.now());
        addressRepository.save(newAddress);
        return null;
    }

    @Override
    public AddressData getAddressById(long id) {
        return null;
    }

    @Override
    public List<AddressData> getAddressByType(int type) {
        return null;
    }

    @Override
    public List<AddressData> getAddressByParentId(long parentId) {
        return null;
    }

    @Override
    public int countAddressByParentId(long parentId) {
        return 0;
    }
}
