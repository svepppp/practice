package io.khasang.ba.dao.impl;

import io.khasang.ba.dao.PointOfInterestDao;
import io.khasang.ba.entity.PointOfInterest;


public class PointOfInterestDaoImpl extends BasicDaoImpl<PointOfInterest> implements PointOfInterestDao {
    public PointOfInterestDaoImpl(Class<PointOfInterest> entityClass) {
        super(entityClass);
    }
}
