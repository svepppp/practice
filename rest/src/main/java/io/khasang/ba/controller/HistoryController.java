package io.khasang.ba.controller;

import io.khasang.ba.entity.History;
import io.khasang.ba.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for REST layer of history management: provided POST, GET, PUT and DELETE functionality
 */
@Controller
@RequestMapping(value = "/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public History addHistory(@RequestBody History newHistory) {
        return historyService.addHistory(newHistory);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public History getHistoryById(@PathVariable(value = "id") long id) {
        return historyService.getHistoryById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public History updateHistory(@RequestBody History updatedHistory) {
        return historyService.updateHistory(updatedHistory);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<History> getAllHistories() {
        return historyService.getAllHistories();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public History deleteHistory(@PathVariable(value = "id") long id) {
        return historyService.deleteHistory(id);
    }
}
