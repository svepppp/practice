package io.khasang.ba.service;

import io.khasang.ba.entity.Document;

import java.util.List;

public interface DocumentService {
    /**
     * method for add new document
     *
     * @param document - document for add
     * @return created document
     */
    Document addDocument(Document document);

    /**
     * method for getting document by id
     *
     * @param id - document's id
     * @return - document by id
     */
    Document getDocumentById(long id);

    /**
     * method for getting all documents
     *
     * @return all documents
     */
    List<Document> getAllDocuments();

    /**
     * method for update document
     *
     * @param document - document with updated params
     * @return updated document
     */
    Document updateDocument(Document document);

    /**
     * method for delete document by id
     *
     * @param id - document's id for delete
     * @return deleted document
     */
    Document deleteDocument(long id);
}