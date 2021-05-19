package ru.borsch.basics.model.action;

public interface BaseAction {
    String getId();
    void executeAction(ActionContext context);
}
