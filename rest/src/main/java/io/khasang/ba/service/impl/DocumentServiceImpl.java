package io.khasang.ba.service.impl;

import io.khasang.ba.dao.DocumentDao;
import io.khasang.ba.entity.Document;
import io.khasang.ba.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentDao documentDao;

    @Override
    public Document addDocument(Document document) {
        return documentDao.add(document);
    }

    @Override
    public Document getDocumentById(long id) {
        return documentDao.getById(id);
    }

    @Override
    public List<Document> getAllDocuments() {
        return documentDao.getAll();
    }

    @Override
    public Document updateDocument(Document document) {
        return documentDao.update(document);
    }

    @Override
    public Document deleteDocument(long id) {
        return documentDao.delete(getDocumentById(id));
    }
}