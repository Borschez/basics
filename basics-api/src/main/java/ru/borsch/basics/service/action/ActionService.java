package ru.borsch.basics.service.action;

import ru.borsch.basics.model.action.BaseAction;

public interface ActionService {
    BaseAction getActionById(String actionId);
}
