package com.demo.PixView.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meta {
    private int page;
    private int pageSize;
    private int totalPage;
    private long totalElements;
}