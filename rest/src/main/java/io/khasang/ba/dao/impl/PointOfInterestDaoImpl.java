package io.khasang.ba.dao.impl;

import io.khasang.ba.dao.PoiDao;
import io.khasang.ba.entity.Poi;


public class PoiDaoImpl extends BasicDaoImpl<Poi> implements PoiDao {
    public PoiDaoImpl(Class<Poi> entityClass) {
        super(entityClass);
    }
}
