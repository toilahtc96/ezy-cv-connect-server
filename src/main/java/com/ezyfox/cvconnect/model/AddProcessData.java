package com.ezyfox.cvconnect.model;

import com.ezyfox.cvconnect.constant.ProcessCode;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddProcessData {
    private ProcessCode processCode;
    private String meaning;
}
