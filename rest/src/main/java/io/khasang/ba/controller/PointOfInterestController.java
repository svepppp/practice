package io.khasang.ba.controller;

import io.khasang.ba.entity.PointOfInterest;
import io.khasang.ba.service.PointOfInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pointOfInterest")
public class PointOfInterestController {

    @Autowired
    PointOfInterestService pointOfInterestService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public PointOfInterest addPointOfInterest(@RequestBody PointOfInterest pointOfInterest) {
        pointOfInterestService.addPointOfInterest(pointOfInterest);
        return pointOfInterest;
    }

    @ResponseBody
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public PointOfInterest getPointOfInterestById(@PathVariable(value = "id") long id) {
        return pointOfInterestService.getPointOfInterestById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public List<PointOfInterest> getAllPointOfInterests() {
        return pointOfInterestService.getAllPointOfInterest();
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public PointOfInterest updatePointOfInterest(@RequestBody PointOfInterest pointOfInterest) {
        return pointOfInterestService.updatePointOfInterest(pointOfInterest);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public PointOfInterest deletePointOfInterest(@PathVariable(value = "id") long id) {
        return pointOfInterestService.deletePointOfInterest(id);
    }
}
