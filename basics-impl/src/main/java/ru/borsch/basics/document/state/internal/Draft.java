package ru.borsch.basics.document.state.internal;

import ru.borsch.basics.model.document.DocumentEntity;

public class Draft extends ru.borsch.basics.document.state.incoming.Draft {

    public Draft(DocumentEntity documentEntity) {
        super(documentEntity);
    }
}
