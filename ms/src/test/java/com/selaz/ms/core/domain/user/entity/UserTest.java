package com.selaz.ms.core.domain.user.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    void getName(){
        User user = new User(Long.valueOf(1), "username", Nivel.ADMIN);
        assertEquals(user.getUsername(), "username");
    }
}
