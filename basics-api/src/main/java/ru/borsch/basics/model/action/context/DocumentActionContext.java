package ru.borsch.basics.model.action.context;

import ru.borsch.basics.model.action.ActionContext;
import ru.borsch.basics.model.document.DocumentEntity;
import ru.borsch.basics.request.RequestParameter;
import ru.borsch.basics.request.proxy.DocumentParameterProxy;
import ru.borsch.basics.request.proxy.TypeParameterProxy;
import ru.borsch.basics.service.document.DocumentEntityService;
import ru.borsch.basics.service.document.DocumentService;

import java.io.Serializable;
import java.util.Map;

public class DocumentActionContext implements ActionContext<DocumentEntity> {
    private DocumentEntity documentEntity;

    public DocumentActionContext(RequestParameter actionParam, DocumentService documentService) {
        String type = (String) new TypeParameterProxy(actionParam).getData();
        DocumentEntityService entityService = documentService.getServiceByDocumentType(type);
        if (entityService != null) {
            DocumentEntity document = entityService.deserialize((Map<String, Serializable>) new DocumentParameterProxy(actionParam).getData());
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
