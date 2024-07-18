package com.demo.PixView.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostPageResponseTest {
    private PostPageResponse postPageResponse;

    @Test
    public void testGettersAndSetters() {
        List<PostResponse> posts = new ArrayList<>();
        Meta meta = new Meta(1, 1, 10, 1L);

        postPageResponse = new PostPageResponse<>(posts, meta);

        assertEquals(posts, postPageResponse.getPosts());
        assertEquals(meta, postPageResponse.getMeta());
    }
}
