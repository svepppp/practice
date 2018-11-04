package io.khasang.ba.dao.impl;

import io.khasang.ba.dao.DocumentItemDao;
import io.khasang.ba.entity.DocumentItem;

public class DocumentItemDaoImpl extends BasicDaoImpl<DocumentItem> implements DocumentItemDao {
    public DocumentItemDaoImpl(Class<DocumentItem> entityClass) {
        super(entityClass);
    }
}
