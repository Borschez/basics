package ru.borsch.basics.model.document;

import ru.borsch.basics.service.document.DocumentService;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class IncomingDocument extends DocumentEntity {

    @Column(length = 256)
    private String sender;

    @Column(length = 256)
    private String addressee;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    @Override
    public String getDocumentTypeCode() {
        return DocumentService.INCOMING_TYPE_CODE;
    }
}
