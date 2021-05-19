package ru.borsch.basics.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.borsch.basics.model.action.ActionContext;
import ru.borsch.basics.model.action.RequestAction;
import ru.borsch.basics.model.action.WrappedAction;
import ru.borsch.basics.model.document.DocumentEntity;
import ru.borsch.basics.model.document.InternalDocument;
import ru.borsch.basics.request.decorators.DocumentParameterDecorator;
import ru.borsch.basics.request.RequestParameter;
import ru.borsch.basics.request.decorators.TypeParameterDecorator;
import ru.borsch.basics.service.document.DocumentEntityService;
import ru.borsch.basics.service.document.DocumentService;


import java.io.Serializable;
import java.util.Map;

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
        DocumentEntity document = ((NotifyExecutorActionContext) context).getData();
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
    public NotifyExecutorActionContext getActionContext(RequestParameter actionParam) {
        return new NotifyExecutorActionContext(actionParam);
    }

    class NotifyExecutorActionContext implements ActionContext<DocumentEntity> {
        private DocumentEntity documentEntity;

        public NotifyExecutorActionContext(RequestParameter actionParam) {
            String type = (String) new TypeParameterDecorator(actionParam).getData();
            DocumentEntityService entityService = documentService.getServiceByDocumentType(type);
            if (entityService != null) {
                DocumentEntity document = entityService.deserialize((Map<String, Serializable>) new DocumentParameterDecorator(actionParam).getData());
                this.documentEntity = (DocumentEntity) entityService.findById(document.getId()).orElse(null);
                if (this.documentEntity == null) {
                    throw new IllegalArgumentException(String.format("Document with type: \"%s\" id: %s not exist in repository", type, document.getId()));
                }
            } else {
                throw new IllegalArgumentException(String.format("Type %s has no assigned entity service", type));
            }
        }

        @Override
        public DocumentEntity getData() {
            return documentEntity;
        }
    }
}
