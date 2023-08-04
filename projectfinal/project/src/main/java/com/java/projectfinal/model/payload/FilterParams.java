package com.java.projectfinal.model.payload;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FilterParams {
    private int page = 0;
    private int results = 10;
    private String sort;
    private String dir = "asc";
}

