package io.khasang.ba.controller;

import io.khasang.ba.entity.History;
import io.khasang.ba.entity.HistoryEventType;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class HistoryControllerIntegrationTest {
    private static final int TEST_ENTITIES_COUNT = 30;

    private static final String ROOT = "http://localhost:8080/history";
    private static final String ADD = "/add";
    private static final String GET_BY_ID = "/get/{id}";
    private static final String GET_ALL = "/get/all";
    private static final String UPDATE = "/update";
    private static final String DELETE_BY_ID = "/delete/{id}";

    /**
     * Check history addition
     */
    @Test
    public void checkAddHistory() {
        LocalDateTime beforeTimestamp = LocalDateTime.now();
        History createdHistory = getCreatedHistory();
        History receivedHistory = getHistoryById(createdHistory.getId());
        LocalDateTime afterTimestamp = LocalDateTime.now();

        assertNotNull(receivedHistory);
        assertEquals(createdHistory, receivedHistory);
        assertEquals(-1, beforeTimestamp.compareTo(receivedHistory.getTimestamp()));
        assertEquals(1, afterTimestamp.compareTo(receivedHistory.getTimestamp()));
    }

    /**
     * Checks sequential addition of certain amount of histories addition and getting. Amount is set in
     * {@link #TEST_ENTITIES_COUNT} constant
     */
    @Test
    public void checkGetAllHistories() {
        List<History> createdHistories = new ArrayList<>(TEST_ENTITIES_COUNT);
        LocalDateTime beforeTimestamp = LocalDateTime.now();
        for (int i = 0; i < TEST_ENTITIES_COUNT; i++) {
            createdHistories.add(getCreatedHistory());
        }
        LocalDateTime afterTimestamp = LocalDateTime.now();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<History>> responseEntity = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<History>>() {
                }
        );
        List<History> allReceivedHistories = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(allReceivedHistories);
        assertFalse(allReceivedHistories.isEmpty());

        List<History> receivedHistoriesSubList =
                allReceivedHistories.subList(allReceivedHistories.size() - TEST_ENTITIES_COUNT, allReceivedHistories.size());
        for (int i = 0; i < TEST_ENTITIES_COUNT; i++) {
            assertEquals(createdHistories.get(i), receivedHistoriesSubList.get(i));
            assertEquals(-1, beforeTimestamp.compareTo(receivedHistoriesSubList.get(i).getTimestamp()));
            assertEquals(1, afterTimestamp.compareTo(receivedHistoriesSubList.get(i).getTimestamp()));
        }
    }

    /**
     * Check of history entity update via PUT request
     */
    @Test
    public void checkUpdateHistory() {
        History history = getCreatedHistory();
        fillHistory(history);

        History updatedHistory = putHistoryToUpdate(history);
        History receivedHistory = getHistoryById(history.getId());
        assertNotNull(updatedHistory);
        assertEquals(updatedHistory, receivedHistory);
    }

    /**
     * Check of history deletion
     */
    @Test
    public void checkHistoryDelete() {
        History history = getCreatedHistory();
        History deletedHistory = getDeletedHistory(history.getId());
        assertEquals(history, deletedHistory);
        assertNull(getHistoryById(history.getId()));
    }

    /**
     * Utility method which deletes history by id and retrieves history entity from DELETE response body
     *
     * @param id Id of the history which should be deleted
     * @return Deleted history
     */
    private History getDeletedHistory(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<History> responseEntity = restTemplate.exchange(
                ROOT + DELETE_BY_ID,
                HttpMethod.DELETE,
                null,
                History.class,
                id
        );
        History deletedHistory = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(deletedHistory);
        return deletedHistory;
    }

    /**
     * Method for history getting by id
     *
     * @param id Id in table of historys
     * @return Found {@link History} instance
     */
    private History getHistoryById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<History> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID,
                HttpMethod.GET,
                null,
                History.class,
                id
        );

        History receivedHistory = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        return receivedHistory;
    }

    /**
     * Put history for update
     *
     * @param history History, which should be updated on service
     */
    private History putHistoryToUpdate(History history) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<History> httpEntity = new HttpEntity<>(history, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<History> responseEntity = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                History.class
        );
        History updatedHistory = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(updatedHistory);
        assertEquals(history.getId(), updatedHistory.getId());
        assertEquals(history.getHistoryEventType(), updatedHistory.getHistoryEventType());
        return updatedHistory;
    }

    /**
     * Get created test history entity from POST response during history creation procedure. Instead of creating {@link History}
     * instance by constructor, this method returns instance from response, thus created history contains table identifier
     *
     * @return Instance of {@link History} with generated identifier
     */
    private History getCreatedHistory() {
        History history = new History();
        fillHistory(history);

        ResponseEntity<History> responseEntity = getHistoryResponseEntityFromAdditionRequest(history);
        History createdHistory = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(createdHistory);
        assertNotNull(createdHistory.getId());
        assertNotNull(createdHistory.getHistoryEventType());
        assertEquals(history.getHistoryEventType(), createdHistory.getHistoryEventType());
        return createdHistory;
    }

    /**
     * Add history entity via POST request
     *
     * @param history {@link History} instance, which should be added via POST request
     * @return {@link ResponseEntity} containing response data
     */
    private ResponseEntity<History> getHistoryResponseEntityFromAdditionRequest(History history) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<History> httpEntity = new HttpEntity<>(history, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                History.class
        );
    }

    /**
     * Fill history parameters
     *
     * @param history {@link History} instance, which should be filled
     */
    private void fillHistory(History history) {
        HistoryEventType[] values = HistoryEventType.values();
        HistoryEventType eventType = values[new Random().nextInt(values.length)];
        history.setHistoryEventType(eventType);
    }
}
