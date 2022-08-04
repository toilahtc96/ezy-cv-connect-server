package com.ezyfox.cvconnect.util;

import com.ezyfox.cvconnect.constant.AddressType;
import com.ezyfox.cvconnect.entity.Address;
import com.ezyfox.cvconnect.repository.AddressRepository;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddressUtil {

    private final AddressRepository addressRepository;

    /*
     * Code = Code parent + chu cai dau + (so ban ghi co chu cai dau giong + 1 ) gom 3 chu so
     *
     * */
    public static String buildCodeOfAddress(
        final AddressType type,
        String name,
        long countOfAddressByNameAndType,
        Address parentAddress
    ) {
        StringBuilder code = new StringBuilder("");
        switch (type) {
            case PROVINCE: {
                String firstLetter = name.substring(0, 1);
                String numberOfAddressCode = String.format("%03d", ++countOfAddressByNameAndType);
                code.append(StringUtil.removeAccent(firstLetter)).append(numberOfAddressCode);
                break;
            }
            case DISTRICT:
            case PRECINCT: {
                if (parentAddress == null) {
                    throw new HttpBadRequestException("Parent id is not found");
                }
                String firstLetter = name.substring(0, 1);
                String numberOfAddressCode = String.format("%03d", ++countOfAddressByNameAndType);
                code
                    .append(parentAddress.getCode())
                    .append(StringUtil.removeAccent(firstLetter))
                    .append(numberOfAddressCode);
                break;
            }

            default:
        }
        return code.toString();
    }
}
