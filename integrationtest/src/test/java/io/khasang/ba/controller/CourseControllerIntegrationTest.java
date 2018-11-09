package io.khasang.ba.controller;

import io.khasang.ba.entity.Course;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Integration test for Course REST layer
 */
public class CourseControllerIntegrationTest {
    private static final String TEST_COURSE_NAME_PREFIX = "TEST_COURSE_";
    private static final String TEST_COURSE_DESCRIPTION = "Test course";
    private static final int TEST_ENTITIES_COUNT = 30;

    private static final String ROOT = "http://localhost:8080/course";
    private static final String ADD = "/add";
    private static final String GET_BY_ID = "/get/{id}";
    private static final String GET_ALL = "/get/all";
    private static final String UPDATE = "/update";
    private static final String DELETE_BY_ID = "/delete/{id}";

    /**
     * Check course addition
     */
    @Test
    public void checkAddCourse() {
        Course createdCourse = getCreatedCourse();
        Course receivedCourse = getCourseById(createdCourse.getId());
        assertNotNull(receivedCourse);
        assertEquals(createdCourse, receivedCourse);
    }

    /**
     * Checks sequential addition of certain amount of courses addition and getting. Amount is set in
     * {@link #TEST_ENTITIES_COUNT} constant
     */
    @Test
    public void checkGetAllCourses() {
        List<Course> createdCourses = new ArrayList<>(TEST_ENTITIES_COUNT);
        for (int i = 0; i < TEST_ENTITIES_COUNT; i++) {
            createdCourses.add(getCreatedCourse());
        }

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Course>> responseEntity = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Course>>() {
                }
        );
        List<Course> allReceivedCourses = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(allReceivedCourses);
        assertFalse(allReceivedCourses.isEmpty());

        List<Course> receivedCoursesSubList =
                allReceivedCourses.subList(allReceivedCourses.size() - TEST_ENTITIES_COUNT, allReceivedCourses.size());
        for (int i = 0; i < TEST_ENTITIES_COUNT; i++) {
            assertEquals(createdCourses.get(i), receivedCoursesSubList.get(i));
        }
    }

    /**
     * Check of course entity update via PUT request
     */
    @Test
    public void checkUpdateCourse() {
        Course course = getCreatedCourse();
        fillCourse(course);

        LocalDateTime timeBeforeUpdate = LocalDateTime.now();
        putCourseToUpdate(course);
        LocalDateTime timeAfterUpdate = LocalDateTime.now();

        Course updatedCourse = getCourseById(course.getId());
        assertNotNull(updatedCourse);
        assertEquals(course.getName(), updatedCourse.getName());
        assertEquals(course.getDescription(), updatedCourse.getDescription());
        assertEquals(course.getDescription(), updatedCourse.getDescription());
        assertEquals(-1, timeBeforeUpdate.compareTo(updatedCourse.getEditionDateTime()));
        assertEquals(1, timeAfterUpdate.compareTo(updatedCourse.getEditionDateTime()));
    }

    /**
     * Check of course deletion
     */
    @Test
    public void checkCourseDelete() {
        Course course = getCreatedCourse();
        Course deletedCourse = getDeletedCourse(course.getId());
        assertEquals(course, deletedCourse);
        assertNull(getCourseById(course.getId()));
    }

    /**
     * Utility method which deletes course by id and retrieves course entity from DELETE response body
     *
     * @param id Id of the course which should be deleted
     * @return Deleted course
     */
    private Course getDeletedCourse(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Course> responseEntity = restTemplate.exchange(
                ROOT + DELETE_BY_ID,
                HttpMethod.DELETE,
                null,
                Course.class,
                id
        );
        Course deletedCourse = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(deletedCourse);
        return deletedCourse;
    }

    /**
     * Method for course getting by id
     *
     * @param id Id in table of courses
     * @return Found {@link Course} instance
     */
    private Course getCourseById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Course> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID,
                HttpMethod.GET,
                null,
                Course.class,
                id
        );
        Course receivedCourse = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        return receivedCourse;
    }

    /**
     * Put course for update
     *
     * @param course Course, which should be updated on service
     */
    private void putCourseToUpdate(Course course) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Course> httpEntity = new HttpEntity<>(course, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Course> responseEntity = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                Course.class
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    /**
     * Get created test course entity from POST response during course creation procedure. Instead of creating {@link Course}
     * instance by constructor, this method returns instance from response, thus created course contains table identifier
     *
     * @return Instance of {@link Course} with generated identifier
     */
    private Course getCreatedCourse() {
        Course course = new Course();
        fillCourse(course);

        LocalDateTime timeBeforeCreation = LocalDateTime.now();
        ResponseEntity<Course> responseEntity = getCourseResponseEntityFromAdditionRequest(course);
        Course createdCourse = responseEntity.getBody();
        LocalDateTime timeAfterCreation = LocalDateTime.now();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(createdCourse);
        assertNotNull(createdCourse.getId());
        assertEquals(course.getDescription(), createdCourse.getDescription());
        assertEquals(course.getName(), createdCourse.getName());
        assertEquals(-1, timeBeforeCreation.compareTo(createdCourse.getEditionDateTime()));
        assertEquals(1, timeAfterCreation.compareTo(createdCourse.getEditionDateTime()));
        return createdCourse;

    }

    /**
     * Add course entity via POST request
     *
     * @param course {@link Course} instance, which should be added via POST request
     * @return {@link ResponseEntity} containing response data
     */
    private ResponseEntity<Course> getCourseResponseEntityFromAdditionRequest(Course course) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Course> httpEntity = new HttpEntity<>(course, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Course.class
        );
    }

    /**
     * Fill test course entity with unique {@link Course#name} for further persistence process.
     * {@link UUID} suffix is used to provide name uniqueness
     */
    private void fillCourse(Course course) {
        course.setName(TEST_COURSE_NAME_PREFIX + UUID.randomUUID().toString());
        course.setDescription(TEST_COURSE_DESCRIPTION);
    }
}
