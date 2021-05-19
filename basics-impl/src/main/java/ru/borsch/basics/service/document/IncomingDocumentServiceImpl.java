package ru.borsch.basics.service.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.borsch.basics.model.document.IncomingDocument;
import ru.borsch.basics.repository.document.IncomingDocumentRepository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("incomingDocumentService")
@Transactional
public class IncomingDocumentServiceImpl implements IncomingDocumentService {

    private IncomingDocumentRepository incomingDocumentRepository;

    @Autowired
    public void setIncomingDocumentRepository(IncomingDocumentRepository incomingDocumentRepository) {
        this.incomingDocumentRepository = incomingDocumentRepository;
    }

    @Override
    public List<IncomingDocument> findByAddressee(String addressee) {
        return incomingDocumentRepository.findByAddressee(addressee);
    }

    @Override
    public Class getDocumentTypeClass() {
        return IncomingDocument.class;
    }

    @Override
    public String getDocumentTypeCode() {
        return "incoming";
    }

    @Override
    public Optional<IncomingDocument> findById(Long id) {
        return incomingDocumentRepository.findById(id);
    }

    @Override
    public List<IncomingDocument> findByRegistrationNumber(String registrationNumber) {
        return incomingDocumentRepository.findByRegistrationNumber(registrationNumber);
    }

    @Override
    public Page<IncomingDocument> findByName(String name, Pageable pageable) {
        return incomingDocumentRepository.findByName(name, pageable);
    }

    @Override
    public List<IncomingDocument> findByState(String state) {
        return incomingDocumentRepository.findByState(state);
    }

    @Override
    public Page<IncomingDocument> findAll(Pageable pageable) {
        return incomingDocumentRepository.findAll(pageable);
    }

    @Override
    public IncomingDocument save(IncomingDocument entity) {
        return incomingDocumentRepository.save(entity);
    }

    @Override
    public IncomingDocument deserialize(Map<String, Serializable> dataMap) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(dataMap, IncomingDocument.class);
    }
}
