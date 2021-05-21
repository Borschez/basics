package ru.borsch.basics.request.proxy;

import ru.borsch.basics.request.Parameter;

import java.io.Serializable;
import java.util.Map;

public class TypeParameterProxy implements Parameter {
    private Parameter original;
    public TypeParameterProxy(Parameter source) {
        this.original = source;
    }

    @Override
    public Serializable getData() {
        return ((Map<String, Serializable>) original.getData()).get("type");
    }
}
