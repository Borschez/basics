package ru.borsch.basics.service.document;

public interface DocumentService {

    String getDocumentTypeCode();
    String getDocumentIdCode();

    void registerService(DocumentEntityService documentEntityService);

    DocumentEntityService getServiceByDocumentType(String documentType);

}
