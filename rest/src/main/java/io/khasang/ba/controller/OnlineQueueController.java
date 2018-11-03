package io.khasang.ba.controller;

import io.khasang.ba.entity.OnlineQueue;
import io.khasang.ba.service.OnlineQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/onlineQueue")
public class OnlineQueueController {

    @Autowired
    private OnlineQueueService onlineQueueService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public OnlineQueue addOnlineQueue(@RequestBody OnlineQueue onlineQueue) {
        onlineQueueService.addOnlineQueue(onlineQueue);
        return onlineQueue;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public OnlineQueue getOnlineQueueById(@PathVariable(value = "id") long id) {
        return onlineQueueService.getOnlineQueueById(id);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<OnlineQueue> getAllOnlineQueue() {
        return onlineQueueService.getAllOnlineQueue();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public OnlineQueue updateOnlineQueue(@RequestBody OnlineQueue onlineQueue) {
        return onlineQueueService.updateOnlineQueue(onlineQueue);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public OnlineQueue deleteOnlineQueue(@PathVariable(value = "id") long id) {
        return onlineQueueService.deleteOnlineQueue(id);
    }
}
