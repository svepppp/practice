package io.khasang.ba.dao.impl;

import io.khasang.ba.dao.NewsDao;
import io.khasang.ba.entity.News;

public class NewsDaoImpl extends BasicDaoImpl<News> implements NewsDao {
    public NewsDaoImpl(Class<News> entityClass) {
        super(entityClass);
    }
}
