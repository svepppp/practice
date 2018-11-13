package io.khasang.ba.controller;

import io.khasang.ba.entity.Course;
import io.khasang.ba.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for REST layer of course management: provided POST, GET, PUT and DELETE functionality
 */
@Controller
@RequestMapping(value = "/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Course addCourse(@RequestBody Course newCourse) {
        return courseService.addCourse(newCourse);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Course getCourseById(@PathVariable(value = "id") long id) {
        return courseService.getCourseById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Course updateCourse(@RequestBody Course updatedCourse) {
        return courseService.updateCourse(updatedCourse);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Course deleteCourse(@PathVariable(value = "id") long id) {
        return courseService.deleteCourse(id);
    }
}
