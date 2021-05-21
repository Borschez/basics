package ru.borsch.basics.model.action;

import ru.borsch.basics.model.document.DocumentEntity;

public interface DocumentAction extends BaseAction {
    ActionContext getActionContext(DocumentEntity documentEntity);
    default void executeAction(DocumentEntity documentEntity) {
        executeAction(getActionContext(documentEntity));
    }
}
