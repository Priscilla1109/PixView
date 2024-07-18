package com.demo.PixView.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MetaTest {
    private Meta meta;

    @Test
    public void testGettersAndSettersAndAllArgsConstructor() {
        int page = 1;
        int pageSize = 1;
        int totalPage = 10;
        Long totalElements = 1L;

        meta = new Meta(page, pageSize, totalPage, totalElements);

        assertEquals(page, meta.getPage());
        assertEquals(pageSize, meta.getPageSize());
        assertEquals(totalPage, meta.getTotalPage());
        assertEquals(totalElements, meta.getTotalElements());
    }

    @Test
    public void testNoArgsConstructor() {
        int page = 1;
        int pageSize = 1;
        int totalPage = 10;
        Long totalElements = 1L;

        meta = new Meta();
        meta.setPage(page);
        meta.setPageSize(pageSize);
        meta.setTotalPage(totalPage);
        meta.setTotalElements(totalElements);

        assertEquals(page, meta.getPage());
        assertEquals(pageSize, meta.getPageSize());
        assertEquals(totalPage, meta.getTotalPage());
        assertEquals(totalElements, meta.getTotalElements());
    }
}