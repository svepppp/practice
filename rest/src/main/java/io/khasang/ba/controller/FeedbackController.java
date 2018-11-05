package io.khasang.ba.controller;

import io.khasang.ba.entity.Feedback;
import io.khasang.ba.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Feedback addFeedback(@RequestBody Feedback feedback) {
        feedbackService.addFeedback(feedback);
        return feedback;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Feedback getFeedbackById(@PathVariable(value = "id") long id) {
        return feedbackService.getFeedbackById(id);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Feedback> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Feedback updateFeedback(@RequestBody Feedback feedback) {
        return feedbackService.updateFeedback(feedback);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Feedback deleteFeedback(@PathVariable(value = "id") long id) {
        return feedbackService.deleteFeedback(id);
    }
}
