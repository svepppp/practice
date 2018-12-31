package io.khasang.ba.controller;

import io.khasang.ba.entity.Request;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RequestControllerIntegrationTest {

    private final static String ROOT = "http://localhost:8080/request";
    private final static String ADD = "/add";
    private final static String GET_BY_ID = "/get";
    private static final String ALL = "/all";
    private static final String DELETE = "/delete";
    private static final String UPDATE = "/update";

    @Test
    public void addRequest() {
        Request request = createdRequest();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Request> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Request.class,
                request.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void checkGetAllRequests() {
        createdRequest();
        createdRequest();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Request>> responseEntity = getListResponseEntity(restTemplate);
        List<Request> requests = responseEntity.getBody();

        for (Request request : requests) {
            assertNotNull(request);
        }
    }

    @Test
    public void deleteRequest() {
        createdRequest();
        Request request = createdRequest();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Request>> responseEntity1 = getListResponseEntity(restTemplate);
        List<Request> requests1 = responseEntity1.getBody();
        ResponseEntity<Request> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "/{id}",
                HttpMethod.DELETE,
                null,
                Request.class,
                request.getId()
        );
        ResponseEntity<List<Request>> responseEntity2 = getListResponseEntity(restTemplate);
        List<Request> requests2 = responseEntity2.getBody();

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertEquals(1, requests1.size() - requests2.size());
    }

    @Test
    public void updateRequest() {
        Request request = createdRequest();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Request requestUpdate = prefillRequest(request.getId());
        HttpEntity<Request> entity = new HttpEntity<>(requestUpdate, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Request> responseEntity = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                Request.class
        );
        Request updatedRequest = responseEntity.getBody();

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(updatedRequest);
        assertEquals(request.getId(), updatedRequest.getId());
    }

    private ResponseEntity<List<Request>> getListResponseEntity(RestTemplate restTemplate) {
        return restTemplate.exchange(
                ROOT + GET_BY_ID + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Request>>() {
                }
        );
    }

    private Request createdRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Request request = prefillRequest();
        HttpEntity<Request> entity = new HttpEntity<>(request, headers);
        RestTemplate restTemplate = new RestTemplate();
        Request createdRequest = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Request.class
        ).getBody();

        assertNotNull(createdRequest);
        assertNotNull(createdRequest.getCategory());
        return createdRequest;
    }

    private Request prefillRequest() {
        Request request = new Request();
        request.setCategory("Трудовой договор");
        request.setDescription("Ляляляля");
        return request;
    }

    private Request prefillRequest(long id) {
        Request request = new Request();
        request.setId(id);
        request.setCategory("Трудовой договор");
        request.setDescription("Траляляляля");
        return request;
    }
}
