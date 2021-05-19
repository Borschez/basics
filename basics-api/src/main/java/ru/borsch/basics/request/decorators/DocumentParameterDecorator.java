package ru.borsch.basics.request.decorators;

import ru.borsch.basics.request.Parameter;

import java.io.Serializable;
import java.util.Map;

public class DocumentParameterDecorator extends ParameterDecorator {
    public DocumentParameterDecorator(Parameter source) {
        super(source);
    }

    @Override
    public Serializable getData() {
        return ((Map<String, Serializable>) wrapped.getData()).get("document");
    }
}
