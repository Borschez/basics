package ru.borsch.basics.model.document;

import ru.borsch.basics.service.document.DocumentService;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class InternalDocument extends DocumentEntity {

    @Column(length = 256)
    private String executor;

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    @Override
    public String getDocumentTypeCode() {
        return DocumentService.INTERNAL_TYPE_CODE;
    }
}
