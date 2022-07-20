package com.ezyfox.cvconnect.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseEntityData {
    private boolean status;
    private String msg;
}
