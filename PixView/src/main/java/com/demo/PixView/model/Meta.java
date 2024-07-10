package com.demo.PixView.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Meta {
    private int page;
    private int pageSize;
    private int totalPage;
    private long totalElements;
}