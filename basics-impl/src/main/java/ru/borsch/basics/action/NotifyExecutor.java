package ru.borsch.basics.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.borsch.basics.model.action.context.DocumentActionContext;
import ru.borsch.basics.model.action.ActionContext;
import ru.borsch.basics.model.action.RequestAction;
import ru.borsch.basics.model.action.WrappedAction;
import ru.borsch.basics.model.document.DocumentEntity;
import ru.borsch.basics.model.document.InternalDocument;
import ru.borsch.basics.request.RequestParameter;
import ru.borsch.basics.service.document.DocumentService;

@Component
public class NotifyExecutor extends WrappedAction implements RequestAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyExecutor.class);

    public final static String ID = "NOTIFY_EXECUTOR";

    private DocumentService documentService;

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public String getId() {
        return this.ID;
    }

    @Override
    protected void beforeExecute(ActionContext context) {
        DocumentEntity document = ((DocumentActionContext) context).getData();
        if (!document.getClass().equals(InternalDocument.class)) {
            throw new IllegalArgumentException(String.format("Action: \"%s\" allowed only for %s", this.getClass(), InternalDocument.class));
        }
        LOGGER.info("Before Execute completed");
    }

    @Override
    protected void execute(ActionContext context) {
        LOGGER.info("Execute completed");
    }

    @Override
    protected void afterExecute(ActionContext context) {
        LOGGER.info("After Execute completed");
    }

    @Override
    public DocumentActionContext getActionContext(RequestParameter actionParam) {
        return new DocumentActionContext(actionParam, documentService);
    }
}
