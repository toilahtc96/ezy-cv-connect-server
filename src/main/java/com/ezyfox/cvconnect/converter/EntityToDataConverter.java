package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.entity.Address;
import com.ezyfox.cvconnect.model.AddressData;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class EntityToDataConverter {

    public AddressData toData(Address address) {
        return AddressData
            .builder()
            .type(address.getType())
            .parentId(address.getParentId())
            .name(address.getName())
            .id(address.getId())
            .code(address.getCode())
            .name(address.getName())
            .build();
    }
}
