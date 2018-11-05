package io.khasang.ba.service;

import io.khasang.ba.entity.DocumentItem;

import java.util.List;

/**
 * Service layer interface for documentItem management
 */
public interface DocumentItemService {

    /**
     * Add new DocumentItem
     *
     * @param documentItem New instance of documentItem
     * @return Added {@link DocumentItem} instance
     */
    DocumentItem addDocumentItem(DocumentItem documentItem);

    /**
     * Get DocumentItem by id
     *
     * @param id Identifier of the desired documentItem
     * @return Found {@link DocumentItem} instance
     */
    DocumentItem getDocumentItemById(long id);

    /**
     * Update existing DocumentItem with new instance
     *
     * @param updatedDocumentItem Updated documentItem instance
     * @return Updated {@link DocumentItem} instance
     */
    DocumentItem updateDocumentItem(DocumentItem updatedDocumentItem);

    /**
     * Get all DocumentItems
     *
     * @return {@link List} instance of all DocumentItems
     */
    List<DocumentItem> getAllDocumentItems();

    /**
     * Delete documentItem by id
     *
     * @param id Identifier of the documentItem which should be deleted
     * @return Deleted {@link DocumentItem} instance
     */
    DocumentItem deleteDocumentItem(long id);
}
