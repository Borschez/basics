package ru.borsch.basics.service.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.borsch.basics.service.document.DocumentService;
import ru.borsch.basics.service.document.IncomingDocumentService;
import ru.borsch.basics.service.document.InternalDocumentService;

import javax.annotation.PostConstruct;

@Component
public class InitializationBean {
    public static final Logger logger = LoggerFactory.getLogger(InitializationBean.class);

    @Autowired
    private DocumentService documentService;

    @Autowired
    private IncomingDocumentService incomingDocumentService;

    @Autowired
    private InternalDocumentService internalDocumentService;


    @PostConstruct
    public void postConstruct() {
        documentService.registerService(incomingDocumentService);
        documentService.registerService(internalDocumentService);

        logger.info("PostConstruct");
    }
}
