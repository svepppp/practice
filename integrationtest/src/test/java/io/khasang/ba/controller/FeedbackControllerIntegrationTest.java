package io.khasang.ba.controller;

import io.khasang.ba.entity.Feedback;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FeedbackControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/feedback";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String UPDATE = "/update";
    private static final String DELETE = "/delete";
    private static final String BY_ID = "/{id}";
    private static final String ALL = "/all";

    @Test
    public void addFeedback() {
        Feedback feedback = createFeedback();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Feedback> responseEntity = restTemplate.exchange(
                ROOT + GET + BY_ID,
                HttpMethod.GET,
                null,
                Feedback.class,
                feedback.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void updateFeedback() {
        Feedback feedback = createFeedback();
        changeFeedback(feedback);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Feedback> entity = new HttpEntity<>(feedback, headers);

        RestTemplate restTemplate = new RestTemplate();
        Feedback updatedFeedback = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                Feedback.class
        ).getBody();

        assertNotNull(updatedFeedback);
        assertEquals(feedback.getTitle(), updatedFeedback.getTitle());
    }

    @Test
    public void deleteOnlineQueue() {
        Feedback feedback = createFeedback();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Feedback> responseDeleteEntity = restTemplate.exchange(
                ROOT + DELETE + BY_ID,
                HttpMethod.DELETE,
                null,
                Feedback.class,
                feedback.getId()
        );

        assertEquals("OK", responseDeleteEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseDeleteEntity.getBody());

        ResponseEntity<Feedback> responseGetEntity = restTemplate.exchange(
                ROOT + GET + BY_ID,
                HttpMethod.GET,
                null,
                Feedback.class,
                feedback.getId()
        );

        assertNull(responseGetEntity.getBody());
    }

    @Test
    public void checkGetAllFeedback() {
        createFeedback();
        createFeedback();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Feedback>> responseEntity = restTemplate.exchange(
                ROOT + GET + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Feedback>>() {
                }
        );

        List<Feedback> feedbacks = responseEntity.getBody();
        assertNotNull(feedbacks.get(0));
        assertNotNull(feedbacks.get(1));
    }

    private Feedback createFeedback() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Feedback feedback = prefillFeedback();
        HttpEntity<Feedback> entity = new HttpEntity<>(feedback, headers);
        RestTemplate restTemplate = new RestTemplate();
        Feedback createdFeedback = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Feedback.class
        ).getBody();

        assertNotNull(createdFeedback);
        assertNotNull(createdFeedback.getTitle());
        return createdFeedback;
    }

    private Feedback prefillFeedback() {
        Feedback feedback = new Feedback();
        feedback.setTitle("B");
        return feedback;
    }

    private void changeFeedback(Feedback feedback) {
        feedback.setTitle("C");
    }
}
