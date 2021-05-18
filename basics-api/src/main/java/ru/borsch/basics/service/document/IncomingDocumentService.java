package ru.borsch.basics.service.document;

import ru.borsch.basics.model.document.IncomingDocument;

import java.util.List;

public interface IncomingDocumentService extends DocumentEntityService<IncomingDocument> {
    List<IncomingDocument> findByAddressee(String addressee);
}
