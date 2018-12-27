package io.khasang.ba.controller;

import io.khasang.ba.entity.PointOfInterest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PointOfInterestControllerIntegrationTest {
    private final String LAN_ADDRESS = "localhost";
    private final String PORT = "8080";
    private final String ROOT = "http://" + LAN_ADDRESS + ":" + PORT + "/pointOfInterest";
    private final String ADD = "/add";
    private final String GET = "/get";
    private final String ID = "/{id}";
    private final String ALL = "/all";
    private final String DELETE = "/delete";
    private final String UPDATE = "/update";

    @Test
    public void addPointOfInterest() {
        PointOfInterest pointOfInterest;
        long pointOfInterestCreateId;
        PointOfInterest pointOfInterestGetById;

        pointOfInterest = createPointOfInterest();
        pointOfInterestCreateId = pointOfInterest.getId();
        pointOfInterestGetById = getPointOfInterestById(pointOfInterestCreateId);
        Assert.assertNotNull(pointOfInterestGetById);
        deletePointOfInterestById(pointOfInterestCreateId);
        pointOfInterestGetById = getPointOfInterestById(pointOfInterestCreateId);
        Assert.assertNull(pointOfInterestGetById);
    }

    private ResponseEntity<PointOfInterest> deletePointOfInterestById(long id) {
        RestTemplate restTemplate;

        restTemplate = new RestTemplate();

        @SuppressWarnings("METHOD")
        ResponseEntity<PointOfInterest> responseEntity = restTemplate.exchange(
                ROOT + DELETE + ID,
                HttpMethod.DELETE,
                null,
                PointOfInterest.class,
                id
        );
        return responseEntity;
    }

    @Test
    public void deletePointOfInterest() {
        PointOfInterest pointOfInterest;

        pointOfInterest = createPointOfInterest();
        ResponseEntity<PointOfInterest> responseEntity = deletePointOfInterestById(pointOfInterest.getId());
        Assert.assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Assert.assertNull(getPointOfInterestById(pointOfInterest.getId()));
    }

    @Test
    public void getAllPointOfInterest() {
        List<PointOfInterest> pointOfInterests;

        pointOfInterests = new ArrayList<>();
        pointOfInterests.add(createPointOfInterest());
        pointOfInterests.add(createPointOfInterest());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<PointOfInterest>> responseEntity = restTemplate.exchange(
                ROOT + GET + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PointOfInterest>>() {
                }
        );
        Assert.assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Assert.assertNotNull(pointOfInterests.get(0));
        Assert.assertNotNull(pointOfInterests.get(1));
        deletePointOfInterestById(pointOfInterests.get(0).getId());
        deletePointOfInterestById(pointOfInterests.get(1).getId());
        Assert.assertNull(getPointOfInterestById(pointOfInterests.get(0).getId()));
        Assert.assertNull(getPointOfInterestById(pointOfInterests.get(1).getId()));
    }

    @Test
    public void updatePointOfInterest() {
        PointOfInterest pointOfInterest;
        RestTemplate restTemplate;
        HttpHeaders httpHeaders;
        HttpEntity<PointOfInterest> entity;

        pointOfInterest = createPointOfInterest();
        restTemplate = new RestTemplate();
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        //Change value column entity pointOfInterest
        pointOfInterest.setName("newTest");
        pointOfInterest.setLatitude(54.333678);
        entity = new HttpEntity<>(pointOfInterest, httpHeaders);
        ResponseEntity<PointOfInterest> responseEntity = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                PointOfInterest.class
        );
        Assert.assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals("newTest", responseEntity.getBody().getName());
        Assert.assertEquals("54.333678", String.valueOf(responseEntity.getBody().getLatitude()));
        deletePointOfInterestById(responseEntity.getBody().getId());
        Assert.assertNull(getPointOfInterestById(responseEntity.getBody().getId()));
    }

    private PointOfInterest createPointOfInterest() {
        PointOfInterest createdPointOfInterest;
        PointOfInterest pointOfInterest;
        RestTemplate restTemplate;
        HttpHeaders httpHeaders;
        HttpEntity<PointOfInterest> entity;
        restTemplate = new RestTemplate();
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        pointOfInterest = prefillPointOfInterest();
        entity = new HttpEntity<>(pointOfInterest, httpHeaders);
        createdPointOfInterest = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                PointOfInterest.class
        ).getBody();

        //  Check POST REST add new pointOfInterest
        Assert.assertNotNull(createdPointOfInterest);
        Assert.assertTrue(createdPointOfInterest.getId() >= 0);
        return createdPointOfInterest;
    }

    private PointOfInterest prefillPointOfInterest() {
        PointOfInterest pointOfInterest = new PointOfInterest();
        pointOfInterest.setCategory("testCategory");
        pointOfInterest.setAddress("testAddress");
        pointOfInterest.setLatitude(0.456000);
        pointOfInterest.setLongitude(3.123456);
        pointOfInterest.setName("testShop");
        pointOfInterest.setStartWork(LocalTime.of(9, 30));

        // 8 hour (input value of min)
        pointOfInterest.setWorkTime(8 * 60);
        return pointOfInterest;
    }

    private PointOfInterest getPointOfInterestById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PointOfInterest> responseEntity = restTemplate.exchange(
                ROOT + GET + ID,
                HttpMethod.GET,
                null,
                PointOfInterest.class,
                id
        );
        Assert.assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        return responseEntity.getBody();
    }
}
