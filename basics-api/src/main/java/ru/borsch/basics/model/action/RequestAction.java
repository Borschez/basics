package ru.borsch.basics.model.action;

import ru.borsch.basics.request.RequestParameter;

public interface RequestAction extends BaseAction {
    ActionContext getActionContext(RequestParameter actionParam);
}
