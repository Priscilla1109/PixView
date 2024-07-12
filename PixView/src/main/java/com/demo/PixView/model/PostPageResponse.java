package com.demo.PixView.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostPageResponse<T> {
    private List<PostResponse> posts;
    private Meta meta;
}
