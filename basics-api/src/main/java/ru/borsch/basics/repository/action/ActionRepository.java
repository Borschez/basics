package ru.borsch.basics.repository.action;

import ru.borsch.basics.model.action.BaseAction;

public interface ActionRepository {
    void save(BaseAction action);

    BaseAction getActionById(String id);
}
