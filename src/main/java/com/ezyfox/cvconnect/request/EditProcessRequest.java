package com.ezyfox.cvconnect.request;

import com.ezyfox.cvconnect.constant.ProcessCode;
import lombok.Data;

@Data
public class EditProcessRequest {
    private long processId;
    private ProcessCode processCode;
    private String meaning;
}
