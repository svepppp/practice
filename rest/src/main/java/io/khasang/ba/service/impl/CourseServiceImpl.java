package io.khasang.ba.service.impl;

import io.khasang.ba.dao.CourseDao;
import io.khasang.ba.entity.Course;
import io.khasang.ba.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of CourseService based on DAO-layer utilization
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    /**
     * Add new course
     *
     * @param newCourse New instance of course
     * @return Added {@link Course} instance
     */
    @Override
    public Course addCourse(Course newCourse) {
        newCourse.setEditionDateTime(LocalDateTime.now());
        return courseDao.add(newCourse);
    }

    /**
     * Get course by id
     *
     * @param id Identifier of the desired course
     * @return Found {@link Course} instance
     */
    @Override
    public Course getCourseById(long id) {
        return courseDao.getById(id);
    }

    /**
     * Update existing course with new instance
     *
     * @param updatedCourse Updated course instance
     * @return Updated {@link Course} instance
     */
    @Override
    public Course updateCourse(Course updatedCourse) {
        updatedCourse.setEditionDateTime(LocalDateTime.now());
        return courseDao.update(updatedCourse);
    }

    /**
     * Get all courses
     *
     * @return {@link List} instance of all courses
     */
    @Override
    public List<Course> getAllCourses() {
        return courseDao.getAll();
    }

    /**
     * Delete course by id
     *
     * @param id Identifier of the course which should be deleted
     * @return Deleted {@link Course} instance
     */
    @Override
    public Course deleteCourse(long id) {
        return courseDao.delete(getCourseById(id));
    }
}
