package ru.borsch.basics.request;

import java.io.Serializable;

public class RequestParameter implements Parameter {
    private Serializable data;

    public Serializable getData() {
        return data;
    }

    public void setData(Serializable data) {
        this.data = data;
    }
}
