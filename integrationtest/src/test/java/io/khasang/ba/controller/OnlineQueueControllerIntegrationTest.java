package io.khasang.ba.controller;

import io.khasang.ba.entity.OnlineQueue;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class OnlineQueueControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/onlineQueue";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String UPDATE = "/update";
    private static final String DELETE = "/delete";
    private static final String BY_ID = "/{id}";
    private static final String ALL = "/all";

    @Test
    public void addOnlineQueue() {
        OnlineQueue onlineQueue = createOnlineQueue();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OnlineQueue> responseEntity = restTemplate.exchange(
                ROOT + GET + BY_ID,
                HttpMethod.GET,
                null,
                OnlineQueue.class,
                onlineQueue.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void updateOnlineQueue() {
        OnlineQueue onlineQueue = createOnlineQueue();
        changeOnlineQueue(onlineQueue);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<OnlineQueue> entity = new HttpEntity<>(onlineQueue, headers);

        RestTemplate restTemplate = new RestTemplate();
        OnlineQueue updatedOnlineQueue = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                OnlineQueue.class
        ).getBody();

        assertNotNull(updatedOnlineQueue);
        assertEquals(onlineQueue.getTitle(), updatedOnlineQueue.getTitle());
    }

    @Test
    public void deleteOnlineQueue() {
        OnlineQueue onlineQueue = createOnlineQueue();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OnlineQueue> responseDeleteEntity = restTemplate.exchange(
                ROOT + DELETE + BY_ID,
                HttpMethod.DELETE,
                null,
                OnlineQueue.class,
                onlineQueue.getId()
        );

        assertEquals("OK", responseDeleteEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseDeleteEntity.getBody());

        ResponseEntity<OnlineQueue> responseGetEntity = restTemplate.exchange(
                ROOT + GET + BY_ID,
                HttpMethod.GET,
                null,
                OnlineQueue.class,
                onlineQueue.getId()
        );

        assertNull(responseGetEntity.getBody());
    }

    @Test
    public void checkGetAllOnlineQueues() {
        createOnlineQueue();
        createOnlineQueue();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<OnlineQueue>> responseEntity = restTemplate.exchange(
                ROOT + GET + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OnlineQueue>>() {
                }
        );

        List<OnlineQueue> onlineQueues = responseEntity.getBody();
        assertNotNull(onlineQueues.get(0));
        assertNotNull(onlineQueues.get(1));
    }

    private OnlineQueue createOnlineQueue() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        OnlineQueue onlineQueue = prefillOnlineQueue();
        HttpEntity<OnlineQueue> entity = new HttpEntity<>(onlineQueue, headers);
        RestTemplate restTemplate = new RestTemplate();
        OnlineQueue createdOnlineQueue = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                OnlineQueue.class
        ).getBody();

        assertNotNull(createdOnlineQueue);
        assertNotNull(createdOnlineQueue.getTitle());
        return createdOnlineQueue;
    }

    private OnlineQueue prefillOnlineQueue() {
        OnlineQueue onlineQueue = new OnlineQueue();
        onlineQueue.setTitle("B");
        return onlineQueue;
    }

    private void changeOnlineQueue(OnlineQueue onlineQueue) {
        onlineQueue.setTitle("C");
    }
}
