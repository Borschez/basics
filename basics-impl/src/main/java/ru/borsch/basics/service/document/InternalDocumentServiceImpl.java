package ru.borsch.basics.service.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.borsch.basics.model.document.InternalDocument;
import ru.borsch.basics.repository.document.InternalDocumentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("internalDocumentService")
@Transactional
public class InternalDocumentServiceImpl implements InternalDocumentService {
    @Autowired
    InternalDocumentRepository internalDocumentRepository;

    @Override
    public List<InternalDocument> findByExecutor(String executor) {
        return internalDocumentRepository.findByExecutor(executor);
    }

    @Override
    public Class getDocumentTypeClass() {
        return InternalDocument.class;
    }

    @Override
    public String getDocumentTypeCode() {
        return "internal";
    }

    @Override
    public Optional<InternalDocument> findById(Long id) {
        return internalDocumentRepository.findById(id);
    }

    @Override
    public List<InternalDocument> findByRegistrationNumber(String registrationNumber) {
        return internalDocumentRepository.findByRegistrationNumber(registrationNumber);
    }

    @Override
    public Page<InternalDocument> findByName(String name, Pageable pageable) {
        return internalDocumentRepository.findByName(name, pageable);
    }

    @Override
    public List<InternalDocument> findByState(String state) {
        return internalDocumentRepository.findByState(state);
    }

    @Override
    public Page<InternalDocument> findAll(Pageable pageable) {
        return internalDocumentRepository.findAll(pageable);
    }

    @Override
    public InternalDocument save(InternalDocument entity) {
        return internalDocumentRepository.save(entity);
    }
}
