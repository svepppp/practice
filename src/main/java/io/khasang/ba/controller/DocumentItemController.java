package io.khasang.ba.controller;

import io.khasang.ba.entity.DocumentItem;
import io.khasang.ba.service.DocumentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for REST layer of documentItem management: provided POST, GET, PUT and DELETE functionality
 */
@Controller
@RequestMapping(value = "/document_item")
public class DocumentItemController {

    @Autowired
    private DocumentItemService documentItemService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public DocumentItem addDocumentItem(@RequestBody DocumentItem newDocumentItem) {
        documentItemService.addDocumentItem(newDocumentItem);
        return newDocumentItem;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public DocumentItem getDocumentItemById(@PathVariable(value = "id") long id) {
        return documentItemService.getDocumentItemById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public DocumentItem updateDocumentItem(@RequestBody DocumentItem updatedDocumentItem) {
        return documentItemService.updateDocumentItem(updatedDocumentItem);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<DocumentItem> getAllDocumentItems() {
        return documentItemService.getAllDocumentItems();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public DocumentItem deleteDocumentItem(@PathVariable(value = "id") long id) {
        return documentItemService.deleteDocumentItem(id);
    }
}
