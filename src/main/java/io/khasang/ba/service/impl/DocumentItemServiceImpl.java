package io.khasang.ba.service.impl;

import io.khasang.ba.dao.DocumentItemDao;
import io.khasang.ba.entity.DocumentItem;
import io.khasang.ba.service.DocumentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of DocumentItemService based on DAO-layer utilization
 */
@Service
public class DocumentItemServiceImpl implements DocumentItemService {

    @Autowired
    private DocumentItemDao documentItemDao;

    /**
     * Add new documentItem
     *
     * @param newDocumentItem New instance of documentItem
     * @return Added {@link DocumentItem} instance
     */
    @Override
    public DocumentItem addDocumentItem(DocumentItem newDocumentItem) {
        return documentItemDao.add(newDocumentItem);
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
        return documentItemDao.delete(getDocumentItemById(id));
    }
}
