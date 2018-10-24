package io.khasang.ba.controller;

import io.khasang.ba.entity.Poi;
import io.khasang.ba.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/poi")
public class PoiController {

    @Autowired
    PoiService poiService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Poi addPoi(@RequestBody Poi poi) {
        poiService.addPoi(poi);
        return poi;
    }

    @ResponseBody
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public Poi getPoiById(@PathVariable(value = "id") long id){
        return poiService.getPoiById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public List<Poi> getAllCats(){
        return poiService.getAllPoi();
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public Poi updatePoi(@RequestBody Poi poi){
        return poiService.updatePoi(poi);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public Poi deleteCat(@PathVariable(value = "id") long id){
        return poiService.deletePoi(id);
    }
}
