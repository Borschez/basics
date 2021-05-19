package ru.borsch.basics.service.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.borsch.basics.action.NotifyExecutor;
import ru.borsch.basics.repository.action.ActionRepository;
import ru.borsch.basics.service.document.DocumentService;
import ru.borsch.basics.service.document.IncomingDocumentService;
import ru.borsch.basics.service.document.InternalDocumentService;

import javax.annotation.PostConstruct;

@Component
public class InitializationBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitializationBean.class);

    private DocumentService documentService;
    private IncomingDocumentService incomingDocumentService;
    private InternalDocumentService internalDocumentService;
    private ActionRepository actionRepository;
    private NotifyExecutor notifyExecutor;

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
    public void setIncomingDocumentService(IncomingDocumentService incomingDocumentService) {
        this.incomingDocumentService = incomingDocumentService;
    }

    @Autowired
    public void setInternalDocumentService(InternalDocumentService internalDocumentService) {
        this.internalDocumentService = internalDocumentService;
    }

    @PostConstruct
    public void postConstruct() {
        documentService.registerService(incomingDocumentService);
        documentService.registerService(internalDocumentService);

        /* Actions */
        actionRepository.save(notifyExecutor);

        LOGGER.info("PostConstruct executed");
    }
}
