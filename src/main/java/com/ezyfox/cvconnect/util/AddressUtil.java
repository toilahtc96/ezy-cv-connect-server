package com.ezyfox.cvconnect.util;

import com.ezyfox.cvconnect.constant.AddressType;
import com.ezyfox.cvconnect.entity.Address;
import com.ezyfox.cvconnect.repository.AddressRepository;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import lombok.AllArgsConstructor;

@EzySingleton
@AllArgsConstructor
public class AddressUtil {

    private final AddressRepository addressRepository;
    private final StringFormatUtil stringFormatUtil;

    /*
     * Code = Code parent + chu cai dau + (so ban ghi co chu cai dau giong + 1 ) gom 3 chu so
     *
     * */
    public String buildCodeOfAddress(final AddressType type, long parentId, String name) {
        StringBuilder code = new StringBuilder("");
        switch (type) {
            case PROVINCE: {
                String firstLetter = name.substring(0, 1);
                long countOfAddressByNameAndType = addressRepository
                    .getCountAddressByNameStartAndType(
                        firstLetter.concat("%"), type.getValue()
                    );
                String numberOfAddressCode = String.format("%03d", ++countOfAddressByNameAndType);
                code.append(stringFormatUtil.removeAccent(firstLetter)).append(numberOfAddressCode);
                break;
            }
            case DISTRICT:
            case PRECINCT: {
                Address parentAddress = addressRepository.findById(parentId);
                if (parentAddress == null) {
                    throw new HttpBadRequestException("Parent id is not found");
                }
                String firstLetter = name.substring(0, 1);
                long countOfAddressByNameAndType = addressRepository
                    .getCountAddressByNameStartAndType(
                        firstLetter.concat("%"), type.getValue()
                    );
                String numberOfAddressCode = String.format("%03d", ++countOfAddressByNameAndType);
                code
                    .append(parentAddress.getCode())
                    .append(stringFormatUtil.removeAccent(firstLetter))
                    .append(numberOfAddressCode);
                break;
            }

            default:
        }
        return code.toString();
    }
}
