package ru.borsch.basics.service.document;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("documentService")
@Transactional
public class DocumentServiceImpl implements DocumentService {

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
    public DocumentEntityService getServiceByDocumentType(String documentType) {
        return registeredService.stream().filter(service -> documentType.equals(service.getDocumentTypeCode())).findFirst().orElse(null);
    }

}
