package io.khasang.ba.service;

import io.khasang.ba.entity.DocumentItem;
import io.khasang.ba.entity.DocumentItemData;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Service layer interface for documentItem management
 */
public interface DocumentItemService {

    /**
     * Add new DocumentItem
     *
     * @param documentItem New instance of documentItem
     * @param documentFile
     * @return Added {@link DocumentItem} instance
     */
    DocumentItem addDocumentItem(DocumentItem documentItem, MultipartFile documentFile) throws IOException;

    /**
     * Get DocumentItem by id
     *
     * @param id Identifier of the desired documentItem
     * @return Found {@link DocumentItem} instance
     */
    DocumentItem getDocumentItemById(long id);

    /**
     * Get data file of DocumentItem, in fact data content of associated {@link DocumentItemData} entity. DocumentItem
     * and DocumentItemData have OneToOne association
     *
     * @param id Identifier of {@link DocumentItem} , i.e. {@link DocumentItemData} identifier
     * @return {@link ResponseEntity} typed with {@link Resource}, that is data file of DocumentItem
     */
    ResponseEntity<Resource> getDocumentItemDataById(long id);

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
