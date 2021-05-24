package ru.borsch.basics.service.document;

import ru.borsch.basics.model.document.DocumentEntity;
import ru.borsch.basics.model.state.State;

import java.io.Serializable;
import java.util.Map;

public interface DocumentService {
    String INCOMING_TYPE_CODE = "incoming";
    String INTERNAL_TYPE_CODE = "internal";

    String getDocumentTypeCode();
    String getDocumentIdCode();

    void registerService(DocumentEntityService documentEntityService);

    DocumentEntityService getEntityServiceByDocumentType(String documentType);
    DocumentEntityService getServiceByDocumentEntity(DocumentEntity documentEntity);

    DocumentEntity deserialize(Map<String, Serializable> dataMap, String typeCode);
    DocumentEntity getPersistent(DocumentEntity documentEntity);
    DocumentEntity save(DocumentEntity documentEntity);

    State getState(DocumentEntity documentEntity);
    void setState(DocumentEntity documentEntity, String state);
}
