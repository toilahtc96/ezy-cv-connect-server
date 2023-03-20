package com.ezyfox.cvconnect.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchWorkingFormData {
    private String name;
    private int page;
    private int size;
}