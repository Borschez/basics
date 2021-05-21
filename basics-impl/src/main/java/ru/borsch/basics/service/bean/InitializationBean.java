package ru.borsch.basics.service.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.borsch.basics.action.NotifyExecutor;
import ru.borsch.basics.repository.action.ActionRepository;
import ru.borsch.basics.service.document.DocumentService;
import ru.borsch.basics.service.document.DocumentStateService;
import ru.borsch.basics.service.document.IncomingDocumentEntityService;
import ru.borsch.basics.service.document.InternalDocumentEntityService;

import javax.annotation.PostConstruct;

@Component
public class InitializationBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitializationBean.class);

    private DocumentService documentService;
    private IncomingDocumentEntityService incomingDocumentEntityService;
    private InternalDocumentEntityService internalDocumentEntityService;
    private ActionRepository actionRepository;
    private NotifyExecutor notifyExecutor;
    private DocumentStateService documentStateService;

    @Autowired
    public void setDocumentStateService(DocumentStateService documentStateService) {
        this.documentStateService = documentStateService;
    }

    @Autowired
    public void setNotifyExecutor(NotifyExecutor notifyExecutor) {
        this.notifyExecutor = notifyExecutor;
    }

    @Autowired
    public void setActionRepository(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Autowired
    public void setIncomingDocumentEntityService(IncomingDocumentEntityService incomingDocumentEntityService) {
        this.incomingDocumentEntityService = incomingDocumentEntityService;
    }

    @Autowired
    public void setInternalDocumentEntityService(InternalDocumentEntityService internalDocumentEntityService) {
        this.internalDocumentEntityService = internalDocumentEntityService;
    }

    @PostConstruct
    public void postConstruct() {
        documentService.registerService(incomingDocumentEntityService);
        documentService.registerService(internalDocumentEntityService);

        /* Actions */
        actionRepository.save(notifyExecutor);

        LOGGER.info("PostConstruct executed");
    }
}
