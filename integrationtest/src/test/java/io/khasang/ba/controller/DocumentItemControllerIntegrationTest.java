package io.khasang.ba.controller;

import io.khasang.ba.entity.DocumentItem;
import io.khasang.ba.entity.DocumentItemType;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Integration test for DocumentItem REST layer
 */
public class DocumentItemControllerIntegrationTest {
    private static final String FIRST_DOCUMENT_ITEM_NAME = "Test_item";
    private static final String SECOND_DOCUMENT_ITEM_NAME = "SECOND_item";
    private static final String UPDATED_DOCUMENT_ITEM_NAME = "UPDATED_DOCUMENT_ITEM_NAME";
    private static final int TEST_ENTITIES_COUNT = 30;

    private static final String ROOT = "http://localhost:8080/document_item";
    private static final String ADD = "/add";
    private static final String GET_BY_ID = "/get/{id}";
    private static final String GET_ALL = "/get/all";
    private static final String UPDATE = "/update";
    private static final String DELETE_BY_ID = "/delete/{id}";

    /**
     * Check documentItem addition
     */
    @Test
    public void checkAddDocumentItem() {
        DocumentItem documentItemElectronic = getCreatedDocumentItem(FIRST_DOCUMENT_ITEM_NAME, DocumentItemType.ELECTRONIC_DOCUMENT);
        DocumentItem receivedDocumentItem = getDocumentItemById(documentItemElectronic.getId());
        assertNotNull(receivedDocumentItem);
        assertEquals(documentItemElectronic, receivedDocumentItem);

        DocumentItem documentItemRaw = getCreatedDocumentItem(SECOND_DOCUMENT_ITEM_NAME, DocumentItemType.RAW_DATA);
        receivedDocumentItem = getDocumentItemById(documentItemRaw.getId());
        assertNotNull(receivedDocumentItem);
        assertEquals(documentItemRaw, receivedDocumentItem);
    }

    /**
     * Checks sequential addition of certain amount of documentItems addition and getting. Amount is set in
     * {@link #TEST_ENTITIES_COUNT} constant
     */
    @Test
    public void checkGetAllDocumentItems() {
        List<DocumentItem> createdDocumentItems = new ArrayList<>(TEST_ENTITIES_COUNT);
        for (int i = 0; i < TEST_ENTITIES_COUNT; i++) {
            createdDocumentItems
                    .add(getCreatedDocumentItem(
                            (i % 2 == 0) ? FIRST_DOCUMENT_ITEM_NAME : SECOND_DOCUMENT_ITEM_NAME,
                            (i % 2 == 0) ? DocumentItemType.ELECTRONIC_DOCUMENT : DocumentItemType.RAW_DATA));
        }

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<DocumentItem>> responseEntity = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DocumentItem>>() {
                }
        );
        List<DocumentItem> allReceivedDocumentItems = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(allReceivedDocumentItems);
        assertFalse(allReceivedDocumentItems.isEmpty());

        List<DocumentItem> receivedDocumentItemsSubList =
                allReceivedDocumentItems.subList(allReceivedDocumentItems.size() - TEST_ENTITIES_COUNT,
                        allReceivedDocumentItems.size());
        for (int i = 0; i < TEST_ENTITIES_COUNT; i++) {
            assertEquals(createdDocumentItems.get(i), receivedDocumentItemsSubList.get(i));
        }
    }

    /**
     * Check of documentItem entity update via PUT request
     */
    @Test
    public void checkUpdateDocumentItem() {
        DocumentItem documentItem =
                getCreatedDocumentItem(FIRST_DOCUMENT_ITEM_NAME, DocumentItemType.ELECTRONIC_DOCUMENT);

        documentItem.setName(UPDATED_DOCUMENT_ITEM_NAME);
        documentItem.setDocumentItemType(DocumentItemType.RAW_DATA);

        putDocumentItemToUpdate(documentItem);
        DocumentItem updatedDocumentItem = getDocumentItemById(documentItem.getId());
        assertNotNull(updatedDocumentItem);
        assertEquals(documentItem, updatedDocumentItem);
    }

    /**
     * Check of documentItem deletion
     */
    @Test
    public void checkDocumentItemDelete() {
        DocumentItem documentItem = getCreatedDocumentItem(FIRST_DOCUMENT_ITEM_NAME,
                DocumentItemType.ELECTRONIC_DOCUMENT);
        DocumentItem deletedDocumentItem = getDeletedDocumentItem(documentItem.getId());
        assertEquals(documentItem, deletedDocumentItem);
        assertNull(getDocumentItemById(documentItem.getId()));
    }

    /**
     * Utility method which deletes documentItem by id and retrieves documentItem entity from DELETE response body
     *
     * @param id Id of the documentItem which should be deleted
     * @return Deleted documentItem
     */
    private DocumentItem getDeletedDocumentItem(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DocumentItem> responseEntity = restTemplate.exchange(
                ROOT + DELETE_BY_ID,
                HttpMethod.DELETE,
                null,
                DocumentItem.class,
                id
        );
        DocumentItem deletedDocumentItem = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(deletedDocumentItem);
        return deletedDocumentItem;
    }

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

    /**
     * Put documentItem for update
     *
     * @param documentItem DocumentItem, which should be updated on service
     */
    private void putDocumentItemToUpdate(DocumentItem documentItem) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<DocumentItem> httpEntity = new HttpEntity<>(documentItem, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DocumentItem> responseEntity = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                DocumentItem.class
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    /**
     * Get created test documentItem entity from POST response during DocumentItem creation procedure.
     * Instead of creating {@link DocumentItem} instance by constructor, this method returns instance
     * from response, thus created documentItem contains table identifier
     *
     * @param documentItemName Name of DocumentItem which should be created
     * @param documentItemType {@link DocumentItemType} of the created DocumentItem
     * @return Instance of {@link DocumentItem} with generated identifier
     */
    private DocumentItem getCreatedDocumentItem(String documentItemName, DocumentItemType documentItemType) {
        DocumentItem documentItem = new DocumentItem();
        documentItem.setName(documentItemName);
        documentItem.setDocumentItemType(documentItemType);
        ResponseEntity<DocumentItem> responseEntity = getDocumentItemResponseEntityFromAdditionRequest(documentItem);
        DocumentItem createdDocumentItem = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(createdDocumentItem);
        assertNotNull(createdDocumentItem.getId());
        return createdDocumentItem;
    }

    /**
     * Add documentItem entity via POST request
     *
     * @param documentItem {@link DocumentItem} instance, which should be added via POST request
     * @return {@link ResponseEntity} containing response data
     */
    private ResponseEntity<DocumentItem> getDocumentItemResponseEntityFromAdditionRequest(DocumentItem documentItem) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<DocumentItem> httpEntity = new HttpEntity<>(documentItem, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                DocumentItem.class
        );
    }
}
