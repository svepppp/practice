package io.khasang.ba.controller;

import io.khasang.ba.entity.DocumentItem;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class DocumentItemControllerIntegrationTest {
    private static final String TEST_DOCUMENT_ITEM_NAME_PREFIX = "TEST_DOCUMENT_ITEM_";
    private static final String SAMPLES_ROOT = "/test_samples/";
    private static final String DOWNLOAD_ROOT = "/download/";
    private static final String FIRST_TEST_FILE_NAME = "test.jpg";
    private static final String SECOND_TEST_FILE_NAME = "test.pdf";
    private static final int TEST_ENTITIES_COUNT = 5;

    private static final String ROOT = "http://localhost:8080/document_item";
    private static final String ADD = "/add";
    private static final String GET_BY_ID = "/get/{id}";
    private static final String GET_DATA_BY_ID = "/get/data/{id}";
    private static final String GET_ALL = "/get/all";
    private static final String UPDATE = "/update";
    private static final String DELETE_BY_ID = "/delete/{id}";

    /**
     * Check documentItem addition
     */
    @Test
    public void checkUploadDocumentItem() throws IOException {
        DocumentItem uploadedDocumentItem = getUploadedDocumentItem(FIRST_TEST_FILE_NAME);
        DocumentItem receivedDocumentItem = getDocumentItemById(uploadedDocumentItem.getId());
        assertNotNull(receivedDocumentItem);
        assertEquals(uploadedDocumentItem, receivedDocumentItem);
        checkFileDownload(receivedDocumentItem);
    }

//    /**
//     * Checks sequential addition of certain amount of documentItems addition and getting. Amount is set in
//     * {@link #TEST_ENTITIES_COUNT} constant
//     */
//    @Test
//    public void checkGetAllDocumentItems() {
//        List<DocumentItem> createdDocumentItems = new ArrayList<>(TEST_ENTITIES_COUNT);
//        for (int i = 0; i < TEST_ENTITIES_COUNT; i++) {
//            createdDocumentItems.add(getUploadedDocumentItem());
//        }
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<List<DocumentItem>> responseEntity = restTemplate.exchange(
//                ROOT + GET_ALL,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<DocumentItem>>() {
//                }
//        );
//        List<DocumentItem> allReceivedDocumentItems = responseEntity.getBody();
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(allReceivedDocumentItems);
//        assertFalse(allReceivedDocumentItems.isEmpty());
//
//        List<DocumentItem> receivedDocumentItemsSubList =
//                allReceivedDocumentItems.subList(allReceivedDocumentItems.size() - TEST_ENTITIES_COUNT, allReceivedDocumentItems.size());
//        for (int i = 0; i < TEST_ENTITIES_COUNT; i++) {
//            assertEquals(createdDocumentItems.get(i), receivedDocumentItemsSubList.get(i));
//        }
//    }

//    /**
//     * Check of documentItem entity update via PUT request
//     */
//    @Test
//    public void checkUpdateDocumentItem() {
//        DocumentItem documentItem = getUploadedDocumentItem();
//        fillDocumentItem(documentItem);
//        putDocumentItemToUpdate(documentItem);
//        DocumentItem updatedDocumentItem = getDocumentItemById(documentItem.getId());
//        assertNotNull(updatedDocumentItem);
//        assertEquals(documentItem, updatedDocumentItem);
//    }

//    /**
//     * Check of documentItem deletion
//     */
//    @Test
//    public void checkDocumentItemDelete() {
//        DocumentItem documentItem = getUploadedDocumentItem();
//        DocumentItem deletedDocumentItem = getDeletedDocumentItem(documentItem.getId());
//        assertEquals(documentItem, deletedDocumentItem);
//        assertNull(getDocumentItemById(documentItem.getId()));
//    }

//    /**
//     * Utility method which deletes documentItem by id and retrieves documentItem entity from DELETE response body
//     *
//     * @param id Id of the documentItem which should be deleted
//     * @return Deleted documentItem
//     */
//    private DocumentItem getDeletedDocumentItem(Long id) {
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<DocumentItem> responseEntity = restTemplate.exchange(
//                ROOT + DELETE_BY_ID,
//                HttpMethod.DELETE,
//                null,
//                DocumentItem.class,
//                id
//        );
//        DocumentItem deletedDocumentItem = responseEntity.getBody();
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(deletedDocumentItem);
//        return deletedDocumentItem;
//    }

    /**
     * Method for documentItem getting by id
     *
     * @param id Id in table of documentItems
     * @return Found {@link DocumentItem} instance
     */
    private DocumentItem getDocumentItemById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DocumentItem> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID,
                HttpMethod.GET,
                null,
                DocumentItem.class,
                id
        );
        DocumentItem receivedDocumentItem = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        return receivedDocumentItem;
    }

//    /**
//     * Put documentItem for update
//     *
//     * @param documentItem DocumentItem, which should be updated on service
//     */
//    private void putDocumentItemToUpdate(DocumentItem documentItem) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        HttpEntity<DocumentItem> httpEntity = new HttpEntity<>(documentItem, httpHeaders);
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<DocumentItem> responseEntity = restTemplate.exchange(
//                ROOT + UPDATE,
//                HttpMethod.PUT,
//                httpEntity,
//                DocumentItem.class
//        );
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//    }

    /**
     * Get created test documentItem entity from POST response during documentItem creation procedure. Instead of creating {@link DocumentItem}
     * instance by constructor, this method returns instance from response, thus created documentItem contains table identifier
     *
     * @return Instance of {@link DocumentItem} with generated identifier
     */
    private DocumentItem getUploadedDocumentItem(String testFileName) {
        DocumentItem documentItem = new DocumentItem();
        documentItem.setName(TEST_DOCUMENT_ITEM_NAME_PREFIX + LocalDateTime.now().toString());
        Resource testResource = getTestResource(testFileName);

        ResponseEntity<DocumentItem> responseEntity
                = getResponseEntityFromPostRequest(documentItem, testResource);
        DocumentItem createdDocumentItem = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(createdDocumentItem);
        assertNotNull(createdDocumentItem.getId());
        return createdDocumentItem;
    }

    private Resource getTestResource(String testFileName) {
        ClassPathResource resource = new ClassPathResource(SAMPLES_ROOT + testFileName);
        assertTrue(resource.exists());
        return resource;
    }

    /**
     * Add documentItem entity via POST request
     *
     * @param documentItem {@link DocumentItem} instance, which should be added via POST request
     * @return {@link ResponseEntity} containing response data
     */
    private ResponseEntity<DocumentItem> getResponseEntityFromPostRequest(DocumentItem documentItem,
                                                                          Resource fileResource) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("document_item", documentItem);
        body.add("file", fileResource);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(body, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                DocumentItem.class
        );
    }

    private void checkFileDownload(DocumentItem receivedDocumentItem) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(
                ROOT + GET_DATA_BY_ID,
                HttpMethod.GET,
                null,
                byte[].class,
                receivedDocumentItem.getId()
        );

        assertEquals(MediaType.valueOf(receivedDocumentItem.getMetadata().getContentType()),
                responseEntity.getHeaders().getContentType());
        assertEquals(receivedDocumentItem.getMetadata().getSize(),
                responseEntity.getHeaders().getContentLength());
        assertNotNull(responseEntity.getBody());
        Path path = Paths.get(new ClassPathResource(SAMPLES_ROOT + FIRST_TEST_FILE_NAME).getFile().getAbsolutePath());
        assertArrayEquals(Files.readAllBytes(path), responseEntity.getBody());
    }
}
