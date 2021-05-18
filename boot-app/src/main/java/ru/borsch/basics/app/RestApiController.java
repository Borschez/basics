package ru.borsch.basics.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.borsch.basics.model.document.DocumentEntity;
import ru.borsch.basics.service.document.DocumentEntityService;
import ru.borsch.basics.service.document.DocumentService;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin
@RequestMapping("/api")
public class RestApiController {
    public static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    DocumentService documentService;

    @RequestMapping(value = "/documents/{type}", method = RequestMethod.GET)
    public ResponseEntity<DocumentEntity> getDocumentsByType(@PathVariable("type") String type, @RequestParam String name,
                                                             @PageableDefault(value = 10, page = 0) Pageable pageable) {
        LOGGER.info("Fetching Documents by Type {}", type);
        DocumentEntityService entityService = documentService.getServiceByDocumentType(type);
        if (entityService != null) {
            if (name == null || name.isEmpty()) {
                return new ResponseEntity(entityService.findAll(pageable).getContent(), HttpStatus.OK);
            } else if (!name.isEmpty()) {
                return new ResponseEntity(entityService.findByName(name, pageable).getContent(), HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
