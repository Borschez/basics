package ru.borsch.basics.repository.document;

import ru.borsch.basics.model.document.IncomingDocument;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface IncomingDocumentRepository extends DocumentEntityRepository<IncomingDocument> {
    List<IncomingDocument> findByAddressee(String addressee);
}
