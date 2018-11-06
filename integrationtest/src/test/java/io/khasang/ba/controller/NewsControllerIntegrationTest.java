package io.khasang.ba.controller;

import io.khasang.ba.entity.News;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NewsControllerIntegrationTest {

    private final static String ROOT = "http://localhost:8080/news";
    private final static String ADD = "/add";
    private final static String GET_BY_ID = "/get";
    private static final String ALL = "/all";
    private static final String DELETE = "/delete";
    private static final String UPDATE = "/update";

    @Test
    public void addNews() {
        News news = createdNews();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<News> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                News.class,
                news.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void checkGetAllNews() {
        createdNews();
        createdNews();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<News>> responseEntity = getListResponseEntity(restTemplate);
        List<News> allNews= responseEntity.getBody();

        for (News news: allNews) {
            assertNotNull(news);
        }
    }

    @Test
    public void deleteNews() {
        createdNews();
        News news = createdNews();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<News>> responseEntity1 = getListResponseEntity(restTemplate);
        List<News> allNews1 = responseEntity1.getBody();
        ResponseEntity<News> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "/{id}",
                HttpMethod.DELETE,
                null,
                News.class,
                news.getId()
        );
        ResponseEntity<List<News>> responseEntity2 = getListResponseEntity(restTemplate);
        List<News> allNews2 = responseEntity2.getBody();

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertEquals(1, allNews1.size() - allNews2.size());
    }

    @Test
    public void updateNews() {
        News news = createdNews();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        News newsUpdate = prefillNews(news.getId());
        HttpEntity<News> entity = new HttpEntity<>(newsUpdate, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<News> responseEntity = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                News.class
        );
        News updatedNews = responseEntity.getBody();

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(updatedNews);
        assertEquals(news.getId(), updatedNews.getId());
    }

    private ResponseEntity<List<News>> getListResponseEntity(RestTemplate restTemplate) {
        return restTemplate.exchange(
                ROOT + GET_BY_ID + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<News>>() {
                }
        );
    }

    private News createdNews() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        News news = prefillNews();
        HttpEntity<News> entity = new HttpEntity<>(news, headers);
        RestTemplate restTemplate = new RestTemplate();
        News createdNews = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                News.class
        ).getBody();

        assertNotNull(createdNews);
        assertNotNull(createdNews.getNews());
        return createdNews;
    }

    private News prefillNews() {
        News news = new News();
        news.setTitle("Schedule of consultations");
        news.setNews("text1.jsp");
        news.setTime(LocalTime.of(10, 11, 34));
        news.setDate(LocalDate.of(2018, 10, 12));
        return news;
    }

    private News prefillNews(long id) {
        News news = new News();
        news.setId(id);
        news.setTitle("New schedule of consultations");
        news.setNews("text2.jsp");
        news.setTime(LocalTime.of(10, 11, 34));
        news.setDate(LocalDate.of(2018, 10, 12));
        return news;
    }
}
