package ru.borsch.basics.model.state;

public interface State {
    static String DRAFT = "Draft";

    String getName();
    String getDocumentType();

    void onEnter();

    void onExit();
}
