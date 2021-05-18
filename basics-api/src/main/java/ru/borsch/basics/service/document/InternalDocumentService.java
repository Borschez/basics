package ru.borsch.basics.service.document;

import ru.borsch.basics.model.document.InternalDocument;

import java.util.List;

public interface InternalDocumentService extends DocumentEntityService<InternalDocument> {
    List<InternalDocument> findByExecutor(String executor);
}
