package ru.borsch.basics.service.document;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.borsch.basics.model.document.DocumentEntity;
import ru.borsch.basics.model.state.State;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("documentService")
@Transactional
public class DocumentServiceImpl implements DocumentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentService.class);

    private DocumentStateService documentStateService;

    @Autowired
    public void setDocumentStateService(DocumentStateService documentStateService) {
        this.documentStateService = documentStateService;
    }

    @Value("${document.service.type.code}")
    private String documentTypeCode;

    @Value("${document.service.id.code}")
    private String documentIdCode;

    private List<DocumentEntityService> registeredService;

    public DocumentServiceImpl() {
        this.registeredService = new ArrayList<>();
    }

    @Override
    public String getDocumentTypeCode() {
        return this.documentTypeCode;
    }

    @Override
    public String getDocumentIdCode() {
        return this.documentIdCode;
    }

    @Override
    public void registerService(DocumentEntityService documentEntityService) {
        this.registeredService.add(documentEntityService);
    }

    @Override
    public DocumentEntityService getEntityServiceByDocumentType(String documentType) {
        return documentType != null ? registeredService.stream().filter(service -> documentType.equals(service.getDocumentTypeCode()))
                .findFirst().orElse(null) : null;
    }

    @Override
    public DocumentEntityService getServiceByDocumentEntity(DocumentEntity documentEntity) {
        return documentEntity != null ? registeredService.stream()
                .filter(service -> documentEntity.getDocumentTypeCode().equals(service.getDocumentTypeCode()))
                .findFirst().orElse(null) : null;
    }

    @Override
    public DocumentEntity deserialize(Map<String, Serializable> dataMap, String typeCode) {
        DocumentEntityService entityService = this.getEntityServiceByDocumentType(typeCode);

        return entityService != null ? entityService.deserialize(dataMap) : null;
    }

    @Override
    public DocumentEntity getPersistent(DocumentEntity documentEntity) {
        DocumentEntityService entityService = this.getServiceByDocumentEntity(documentEntity);
        if (entityService != null) {
            return (DocumentEntity) entityService.findById(documentEntity.getId()).orElse(null);
        }

        return null;
    }

    @Override
    public DocumentEntity save(DocumentEntity documentEntity) {
        DocumentEntityService entityService = this.getServiceByDocumentEntity(documentEntity);
        if (entityService != null) {
            if (entityService.findById(documentEntity.getId()).isPresent()) {
                LOGGER.info("Update Document: {}  ", documentEntity);
            } else {
                LOGGER.info("Create Document: {}  ", documentEntity);
            }
            return entityService.save(documentEntity);
        }

        return null;
    }

    @Override
    public State getState(DocumentEntity documentEntity) {
        return documentStateService.getState(documentEntity);
    }

    @Override
    public void setState(DocumentEntity documentEntity, String state) {
        documentStateService.setState(documentEntity, state);
    }

}
