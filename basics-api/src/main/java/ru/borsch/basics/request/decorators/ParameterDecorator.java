package ru.borsch.basics.request.decorators;

import ru.borsch.basics.request.Parameter;

import java.io.Serializable;

public class ParameterDecorator implements Parameter {
    protected Parameter wrapped;

    public ParameterDecorator(Parameter source) {
        this.wrapped = source;
    }
    @Override
    public Serializable getData() {
        return wrapped.getData();
    }
}
