package com.ezyfox.cvconnect.builder;

import com.ezyfox.cvconnect.constant.VoucherType;
import com.ezyfox.cvconnect.entity.Voucher;
import com.ezyfox.cvconnect.util.CurrencyUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VoucherInfoBuilder {
    public String buildByVoucher(Voucher voucher) {
        StringBuilder voucherInfo = new StringBuilder("Bonus: ");
        if (VoucherType.CASH.equals(voucher.getVoucherType())) {
            voucherInfo.append(CurrencyUtil.vnFormat(voucher.getValue())).append(" đ");
        }
        if (VoucherType.PERCENT.equals(voucher.getVoucherType())) {
            voucherInfo.append((int) voucher.getValue()).append(" % Salary Offer");
        }
        return voucherInfo.toString();
    }
}
