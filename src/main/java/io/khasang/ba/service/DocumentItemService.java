package io.khasang.ba.service;

import io.khasang.ba.entity.DocumentItem;

import java.util.List;

/**
 * Service layer interface for documentItem management
 */
public interface DocumentItemService {

    /**
     * Add new documentItem
     *
     * @param newDocumentItem New instance of documentItem
     * @return Added {@link DocumentItem} instance
     */
    DocumentItem addDocumentItem(DocumentItem newDocumentItem);

    /**
     * Get documentItem by id
     *
     * @param id Identifier of the desired documentItem
     * @return Found {@link DocumentItem} instance
     */
    DocumentItem getDocumentItemById(long id);

    /**
     * Update existing documentItem with new instance
     *
     * @param updatedDocumentItem Updated documentItem instance
     * @return Updated {@link DocumentItem} instance
     */
    DocumentItem updateDocumentItem(DocumentItem updatedDocumentItem);

    /**
     * Get all documentItems
     *
     * @return {@link List} instance of all documentItems
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
