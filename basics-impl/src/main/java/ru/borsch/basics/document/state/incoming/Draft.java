package ru.borsch.basics.document.state.incoming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.borsch.basics.model.document.DocumentEntity;
import ru.borsch.basics.model.state.State;
import ru.borsch.basics.service.document.DocumentService;

public class Draft implements State {
    private static final Logger LOGGER = LoggerFactory.getLogger(State.class);

    private DocumentEntity documentEntity;

    public Draft(DocumentEntity documentEntity) {
        this.documentEntity = documentEntity;
    }

    @Override
    public String getName() {
        return State.DRAFT;
    }

    @Override
    public String getDocumentType() {
        return DocumentService.INCOMING_TYPE_CODE;
    }

    @Override
    public void onEnter() {
       LOGGER.info("Enter in Draft State");
    }

    @Override
    public void onExit() {
        LOGGER.info("Exit from Draft State");
    }
}
