package ru.borsch.basics.service.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.borsch.basics.model.document.InternalDocument;
import ru.borsch.basics.repository.document.InternalDocumentRepository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("internalDocumentEntityService")
@Transactional
public class InternalDocumentEntityServiceImpl implements InternalDocumentEntityService {

    private InternalDocumentRepository internalDocumentRepository;

    @Autowired
    public void setInternalDocumentRepository(InternalDocumentRepository internalDocumentRepository) {
        this.internalDocumentRepository = internalDocumentRepository;
    }

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
        return DocumentService.INTERNAL_TYPE_CODE;
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

    @Override
    public InternalDocument deserialize(Map<String, Serializable> dataMap) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(dataMap, InternalDocument.class);
    }
}
