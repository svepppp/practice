package io.khasang.ba.controller;

import io.khasang.ba.entity.User;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserControllerIntegrationTest {

    private final static String ROOT = "http://localhost:8080/user";
    private final static String ADD = "/add";
    private final static String GET_BY_ID = "/get";
    private static final String ALL = "/all";
    private static final String DELETE = "/delete";
    private static final String UPDATE = "/update";

    @Test
    public void addUser() {
        User user = createdUser();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                User.class,
                user.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void checkGetAllUsers() {
        createdUser();
        createdUser();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<User>> responseEntity = getListResponseEntity(restTemplate);
        List<User> users = responseEntity.getBody();

        for (User user : users) {
            assertNotNull(user);
        }
    }

    @Test
    public void deleteUser() {
        createdUser();
        User user = createdUser();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<User>> responseEntity1 = getListResponseEntity(restTemplate);
        List<User> users1 = responseEntity1.getBody();
        ResponseEntity<User> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "/{id}",
                HttpMethod.DELETE,
                null,
                User.class,
                user.getId()
        );
        ResponseEntity<List<User>> responseEntity2 = getListResponseEntity(restTemplate);
        List<User> users2 = responseEntity2.getBody();

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertEquals(1, users1.size() - users2.size());
    }

    @Test
    public void updateUser() {
        User user = createdUser();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        User userUpdate = prefillUser(user.getId());
        HttpEntity<User> entity = new HttpEntity<>(userUpdate, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> responseEntity = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                User.class
        );
        User updatedUser = responseEntity.getBody();

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(updatedUser);
        assertEquals(user.getId(), updatedUser.getId());
    }

    private ResponseEntity<List<User>> getListResponseEntity(RestTemplate restTemplate) {
        return restTemplate.exchange(
                ROOT + GET_BY_ID + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }
        );
    }

    private User createdUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        User user = prefillUser();
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        RestTemplate restTemplate = new RestTemplate();
        User createdUser = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                User.class
        ).getBody();

        assertNotNull(createdUser);
        assertNotNull(createdUser.getName());
        return createdUser;
    }

    private User prefillUser() {
        User user = new User();
        user.setName("Anna");
        user.setSurname("Ivanova");
        user.setDescription("Consultation");
        user.setTime(LocalTime.of(10, 11, 34));
        user.setDate(LocalDate.of(2018, 10, 12));
        return user;
    }

    private User prefillUser(long id) {
        User user = new User();
        user.setId(id);
        user.setName("Inga");
        user.setSurname("Petrova");
        user.setDescription("Consultation");
        user.setTime(LocalTime.of(10, 11, 34));
        user.setDate(LocalDate.of(2018, 10, 12));
        return user;
    }
}
