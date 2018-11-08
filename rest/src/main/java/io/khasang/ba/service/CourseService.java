package io.khasang.ba.service;

import io.khasang.ba.entity.Course;

import java.util.List;

/**
 * Service layer interface for course management
 */
public interface CourseService {

    /**
     * Add new course
     *
     * @param newCourse New instance of course
     * @return Added {@link Course} instance
     */
    Course addCourse(Course newCourse);

    /**
     * Get course by id
     *
     * @param id Identifier of the desired course
     * @return Found {@link Course} instance
     */
    Course getCourseById(long id);

    /**
     * Update existing course with new instance
     *
     * @param updatedCourse Updated course instance
     * @return Updated {@link Course} instance
     */
    Course updateCourse(Course updatedCourse);

    /**
     * Get all courses
     *
     * @return {@link List} instance of all courses
     */
    List<Course> getAllCourses();

    /**
     * Delete course by id
     *
     * @param id Identifier of the course which should be deleted
     * @return Deleted {@link Course} instance
     */
    Course deleteCourse(long id);
}
