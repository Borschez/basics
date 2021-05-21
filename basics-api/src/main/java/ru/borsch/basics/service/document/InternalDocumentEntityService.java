package ru.borsch.basics.service.document;

import ru.borsch.basics.model.document.InternalDocument;

import java.util.List;

public interface InternalDocumentEntityService extends DocumentEntityService<InternalDocument> {
    List<InternalDocument> findByExecutor(String executor);
}
