package io.khasang.ba.controller;

import io.khasang.ba.entity.Poi;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PoiControllerIntegrationTest {
    private final String LAN_ADDRESS = "localhost";
    private final String PORT = "8080";
    private final String ROOT = "http://" + LAN_ADDRESS + ":" + PORT + "/poi";
    private final String ADD = "/add";
    private final String GET = "/get";
    private final String ID = "/{id}";
    private final String ALL = "/all";
    private final String DELETE = "/delete";
    private final String UPDATE = "/update";

    @Test
    public void addPoi() {
        Poi poi;
        long poiCreateId;
        Poi poiGetById;

        poi = createPoi();
        poiCreateId = poi.getId();
        poiGetById = getPoiById(poiCreateId);
        Assert.assertNotNull(poiGetById);
        deletePoiById(poiCreateId);
        poiGetById = getPoiById(poiCreateId);
        Assert.assertNull(poiGetById);
    }

    private ResponseEntity<Poi> deletePoiById(long id) {
        RestTemplate restTemplate;

        restTemplate = new RestTemplate();

        @SuppressWarnings("METHOD")
        ResponseEntity<Poi> responseEntity = restTemplate.exchange(
                ROOT + DELETE + ID,
                HttpMethod.DELETE,
                null,
                Poi.class,
                id
        );
        return responseEntity;
    }

    @Test
    public void deletePoi() {
        Poi poi;

        poi = createPoi();
        ResponseEntity<Poi> responseEntity = deletePoiById(poi.getId());
        Assert.assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Assert.assertNull(getPoiById(poi.getId()));
    }

    @Test
    public void getAllPoi() {
        List<Poi> pois;

        pois = new ArrayList<>();
        pois.add(createPoi());
        pois.add(createPoi());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Poi>> responseEntity = restTemplate.exchange(
                ROOT + GET + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Poi>>() {
                }
        );
        Assert.assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Assert.assertNotNull(pois.get(0));
        Assert.assertNotNull(pois.get(1));
        deletePoiById(pois.get(0).getId());
        deletePoiById(pois.get(1).getId());
        Assert.assertNull(getPoiById(pois.get(0).getId()));
        Assert.assertNull(getPoiById(pois.get(1).getId()));
    }

    @Test
    public void updatePoi() {
        Poi poi;
        RestTemplate restTemplate;
        HttpHeaders httpHeaders;
        HttpEntity<Poi> entity;

        poi = createPoi();
        restTemplate = new RestTemplate();
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        //Change value column entity POI
        poi.setName("newTest");
        poi.setLatitude(54.333678);
        entity = new HttpEntity<>(poi, httpHeaders);
        ResponseEntity<Poi> responseEntity = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                Poi.class
        );
        Assert.assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals("newTest", responseEntity.getBody().getName());
        Assert.assertEquals("54.333678", String.valueOf(responseEntity.getBody().getLatitude()));
        deletePoiById(responseEntity.getBody().getId());
        Assert.assertNull(getPoiById(responseEntity.getBody().getId()));
    }

    private Poi createPoi() {
        Poi createdPoi;
        Poi poi;
        RestTemplate restTemplate;
        HttpHeaders httpHeaders;
        HttpEntity<Poi> entity;
        restTemplate = new RestTemplate();
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        poi = prefillPoi();
        entity = new HttpEntity<>(poi, httpHeaders);
        createdPoi = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Poi.class
        ).getBody();

        //  Check POST REST add new POI
        Assert.assertNotNull(createdPoi);
        Assert.assertTrue(createdPoi.getId() >= 0);
        return createdPoi;
    }

    private Poi prefillPoi() {
        Poi poi = new Poi();
        poi.setCategory("testCategory");
        poi.setAddress("testAddress");
        poi.setLatitude(0.456000);
        poi.setLongitude(3.123456);
        poi.setName("testShop");
        poi.setStartWork(LocalTime.of(9, 30));

        // 8 hour (input value of min)
        poi.setWorkTime(8 * 60);
        return poi;
    }

    private Poi getPoiById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Poi> responseEntity = restTemplate.exchange(
                ROOT + GET + ID,
                HttpMethod.GET,
                null,
                Poi.class,
                id
        );
        Assert.assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        return responseEntity.getBody();
    }
}
