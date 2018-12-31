package io.khasang.ba.controller;

import io.khasang.ba.entity.Request;
import io.khasang.ba.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Request addRequest(@RequestBody Request request) {
        requestService.addRequest(request);
        return request;
    }

    @ResponseBody
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public Request getRequestById(@PathVariable(value = "id") long id) {
        return requestService.getRequestById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public List<Request> getAllRequests() {
        return requestService.getAllRequests();
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public Request updateRequest(@RequestBody Request request) {
        return requestService.updateRequest(request);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public Request deleteRequest(@PathVariable(value = "id") long id) {
        return requestService.deleteRequest(id);
    }
}
