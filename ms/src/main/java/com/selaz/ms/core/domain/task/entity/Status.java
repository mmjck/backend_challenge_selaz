package com.selaz.ms.core.domain.task.entity;

public enum Status {

    PENDENTE(1, "pendente"),
    CONCLUIDA(2, "concluida"),
    EM_ANDAMENTO(3, "em_andamento");

    private int value;
    private String message;

    private Status(int value, String message) {
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
