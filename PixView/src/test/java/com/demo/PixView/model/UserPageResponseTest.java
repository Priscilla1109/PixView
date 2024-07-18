package com.demo.PixView.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserPageResponseTest {
    private UserPageResponse userPageResponse;

    @Test
    public void testGettersAndSetters() {
        List<UserResponse> users = new ArrayList<>();
        Meta meta = new Meta(1,1,10,1L);

        userPageResponse = new UserPageResponse<>(users, meta);

        assertEquals(users, userPageResponse.getUsers());
        assertEquals(meta, userPageResponse.getMeta());
    }
}
