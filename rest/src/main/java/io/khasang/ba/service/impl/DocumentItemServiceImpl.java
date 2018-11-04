package io.khasang.ba.service.impl;

import io.khasang.ba.dao.DocumentItemDao;
import io.khasang.ba.dao.DocumentItemDataDao;
import io.khasang.ba.entity.DocumentItem;
import io.khasang.ba.entity.DocumentItemData;
import io.khasang.ba.entity.DocumentItemMetadata;
import io.khasang.ba.service.DocumentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Implementation of DocumentItemService based on DAO-layer utilization
 */
@Service
public class DocumentItemServiceImpl implements DocumentItemService {

    @Autowired
    private DocumentItemDao documentItemDao;

    @Autowired
    private DocumentItemDataDao documentItemDataDao;

    /**
     * Add new documentItem
     *
     * @param documentItem New instance of documentItem
     * @param documentFile
     * @return Added {@link DocumentItem} instance
     */
    @Override
    public DocumentItem addDocumentItem(DocumentItem documentItem, MultipartFile documentFile) throws IOException {
        DocumentItem addedDocumentItem = new DocumentItem(documentItem, documentFile);
        if (!documentFile.isEmpty()) {
            addedDocumentItem = documentItemDao.add(addedDocumentItem);
            DocumentItemData documentItemData = new DocumentItemData(addedDocumentItem, documentFile);
            documentItemDataDao.add(documentItemData);
        }
        return addedDocumentItem;
    }

    /**
     * Get documentItem by id
     *
     * @param id Identifier of the desired documentItem
     * @return Found {@link DocumentItem} instance
     */
    @Override
    public DocumentItem getDocumentItemById(long id) {
        return documentItemDao.getById(id);
    }

    /**
     * Get data file of DocumentItem, in fact data content of associated {@link DocumentItemData} entity. DocumentItem
     * and DocumentItemData have OneToOne association
     *
     * @param id Identifier of {@link DocumentItem} , i.e. {@link DocumentItemData} identifier
     * @return {@link ResponseEntity} typed with {@link Resource}, that is data file of DocumentItem
     */
    @Override
    public ResponseEntity<Resource> getDocumentItemDataById(long id) {
        ResponseEntity<Resource> responseEntity = null;
        DocumentItem documentItem = getDocumentItemById(id);
        if (documentItem != null) {
            DocumentItemMetadata metadata = getDocumentItemById(id).getMetadata();
            DocumentItemData documentItemData = documentItemDataDao.getById(id);
            if (metadata != null && documentItemData != null) {
                ByteArrayResource resource = new ByteArrayResource(documentItemData.getData());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.valueOf(metadata.getContentType()));
                headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + metadata.getFileName() + '"');
                headers.setContentLength(resource.contentLength());
                responseEntity = new ResponseEntity<>(resource, headers, HttpStatus.OK);
            }
        }

        return responseEntity;
    }

    /**
     * Update existing documentItem with new instance
     *
     * @param updatedDocumentItem Updated documentItem instance
     * @return Updated {@link DocumentItem} instance
     */
    @Override
    public DocumentItem updateDocumentItem(DocumentItem updatedDocumentItem) {
        return documentItemDao.update(updatedDocumentItem);
    }

    /**
     * Get all documentItems
     *
     * @return {@link List} instance of all documentItems
     */
    @Override
    public List<DocumentItem> getAllDocumentItems() {
        return documentItemDao.getAll();
    }

    /**
     * Delete documentItem by id
     *
     * @param id Identifier of the documentItem which should be deleted
     * @return Deleted {@link DocumentItem} instance
     */
    @Override
    public DocumentItem deleteDocumentItem(long id) {
//        documentItemDataDao.delete(getDocumentItemDataById(id));
        return documentItemDao.delete(getDocumentItemById(id));
    }
}
