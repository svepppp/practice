package io.khasang.ba.controller;

import io.khasang.ba.entity.Document;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DocumentControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/document";
    private static final String ADD = "/add";
    private static final String GET_BY_ID = "/get";
    private static final String ALL = "/all";
    private static final String UPDATE = "/update";
    private static final String DELETE = "/delete";

    @Test
    public void addDocument() {
        Document document = createDocument();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Document> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Document.class,
                document.getId()
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void checkGetAllDocuments() {
        createDocument();
        createDocument();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Document>> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Document>>() {
                }
        );
        List<Document> documents = responseEntity.getBody();
        assertNotNull(documents.get(0));
        assertNotNull(documents.get(1));
    }

    @Test
    public void checkUpdateDocument() {
        Document document = createDocument();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Document> entity = new HttpEntity<>(document, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Document> responseEntity = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                Document.class,
                document.getId()
        );
        Document updatedDocument = updateDocument(document);
        assertNotNull(document);
        assertNotNull(updatedDocument);
        assertEquals("OK", document, updatedDocument);
    }

    @Test
    public void checkDeleteDocument() {
        Document document = createDocument();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Document> entity = new HttpEntity<>(document, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Document> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "/{id}",
                HttpMethod.DELETE,
                entity,
                Document.class,
                document.getId()
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
    }

    private Document updateDocument(Document document) {
        document.setIssuerName("Ivan Petrovich");
        document.setDescription("updated");
        document.setDateOfIssue(LocalDate.of(2018, 12, 31));
        return document;
    }

    private Document createDocument() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Document document = prefillDocument();
        HttpEntity<Document> entity = new HttpEntity<>(document, headers);
        RestTemplate restTemplate = new RestTemplate();
        Document createdDocument = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Document.class
        ).getBody();
        assertNotNull(createdDocument);
        assertNotNull(createdDocument.getIssuerName());
        assertNotNull(createdDocument.getDescription());
        return createdDocument;
    }

    private Document prefillDocument() {
        Document document = new Document();
        document.setDateOfIssue(LocalDate.of(2018, 10, 21));
        document.setIssuerName("Petr Ivanov");
        document.setDescription("reference");
        return document;
    }
}