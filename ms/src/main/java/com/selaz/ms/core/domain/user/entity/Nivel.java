package com.selaz.ms.core.domain.user.entity;

public enum Nivel {
    ADMIN(1, "admin"),
    USER(2, "user");

    private int value;
    private String message;

    private Nivel(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return this.value;
    }

    public String getMessate(){
        return this.message;
    }

}
