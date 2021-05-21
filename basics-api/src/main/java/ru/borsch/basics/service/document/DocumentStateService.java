package ru.borsch.basics.service.document;

import ru.borsch.basics.model.document.DocumentEntity;
import ru.borsch.basics.model.state.State;

public interface DocumentStateService {
    State getState(DocumentEntity documentEntity);
    void setState(DocumentEntity documentEntity, String state);
}
