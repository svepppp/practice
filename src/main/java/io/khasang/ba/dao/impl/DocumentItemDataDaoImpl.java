package io.khasang.ba.dao.impl;

import io.khasang.ba.dao.DocumentItemDataDao;
import io.khasang.ba.entity.DocumentItemData;

public class DocumentItemDataDaoImpl extends BasicDaoImpl<DocumentItemData> implements DocumentItemDataDao {
    public DocumentItemDataDaoImpl(Class<DocumentItemData> entityClass) {
        super(entityClass);
    }
}
