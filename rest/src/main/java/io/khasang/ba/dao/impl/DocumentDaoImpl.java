package io.khasang.ba.dao.impl;

import io.khasang.ba.dao.DocumentDao;
import io.khasang.ba.entity.Document;

public class DocumentDaoImpl extends BasicDaoImpl<Document> implements DocumentDao {
    public DocumentDaoImpl(Class<Document> entityClass) {
        super(entityClass);
    }
}