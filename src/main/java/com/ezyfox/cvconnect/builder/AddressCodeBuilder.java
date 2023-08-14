package com.ezyfox.cvconnect.builder;

import com.ezyfox.cvconnect.constant.AddressType;
import com.ezyfox.cvconnect.entity.Address;
import com.ezyfox.cvconnect.util.StringUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressCodeBuilder {

    private AddressType type;
    private String name;
    private Address parentAddress;
    private long countOfAddressByNameAndType;

    public AddressCodeBuilder type(AddressType type) {
        this.type = type;
        return this;
    }

    public String build() {
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
                String firstLetter = name.substring(0, 1);
                String numberOfAddressCode = String.format("%03d", ++countOfAddressByNameAndType);
                code
                    .append(StringUtil.removeAccent(firstLetter))
                    .append(numberOfAddressCode);
                //                    .append(parentAddress == null ? "" : parentAddress.getCode())
                break;
            }

            default:
        }
        return code.toString();
    }
}
