package ru.borsch.basics.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.borsch.basics.model.action.RequestAction;
import ru.borsch.basics.model.document.DocumentEntity;
import ru.borsch.basics.request.RequestParameter;
import ru.borsch.basics.service.action.ActionService;
import ru.borsch.basics.service.document.DocumentEntityService;
import ru.borsch.basics.service.document.DocumentService;

import java.io.Serializable;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin
@RequestMapping("/api")
public class RestApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);

    private DocumentService documentService;
    private ActionService actionService;

    @Autowired
    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @RequestMapping(value = "/action/{actionId}/execute", method = RequestMethod.POST)
    public ResponseEntity executeAction(@PathVariable("actionId") String actionId, @RequestBody RequestParameter actionParam) {
        LOGGER.info("Execute \"{}\" Action: {}  ", actionId, actionParam.getData());
        RequestAction action = (RequestAction) actionService.getActionById(actionId);
        if (action != null) {
            action.executeAction(action.getActionContext(actionParam));
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/document/{type}", method = RequestMethod.POST)
    public ResponseEntity<DocumentEntity> createDocument(@PathVariable("type") String type, @RequestBody RequestParameter documentParam) {
        LOGGER.info("Create {} Document: {}  ", type, documentParam.getData());
        DocumentEntityService entityService = documentService.getServiceByDocumentType(type);
        if (entityService != null) {
            return new ResponseEntity(entityService.save(entityService.deserialize((Map<String, Serializable>) documentParam.getData())), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/documents/{type}", method = RequestMethod.GET)
    public ResponseEntity<DocumentEntity> getDocumentsByType(@PathVariable("type") String type,
                                                             @PageableDefault(value = 10, page = 0) Pageable pageable) {
        LOGGER.info("Fetching Documents by Type {}", type);
        DocumentEntityService entityService = documentService.getServiceByDocumentType(type);
        if (entityService != null) {
            return new ResponseEntity(entityService.findAll(pageable).getContent(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
