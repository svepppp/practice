package io.khasang.ba.service.impl;

import io.khasang.ba.dao.PointOfInterestDao;
import io.khasang.ba.entity.PointOfInterest;
import io.khasang.ba.service.PointOfInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointOfInterestServiceImpl implements PointOfInterestService {

    @Autowired
    private PointOfInterestDao pointOfInterestDao;

    @Override
    public PointOfInterest addPointOfInterest(PointOfInterest pointOfInterest) {
        pointOfInterestDao.add(pointOfInterest);
        return pointOfInterest;
    }

    @Override
    public PointOfInterest getPointOfInterestById(long id) {
        return pointOfInterestDao.getById(id);
    }

    @Override
    public List<PointOfInterest> getAllPointOfInterest() {
        return pointOfInterestDao.getAll();
    }

    @Override
    public PointOfInterest updatePointOfInterest(PointOfInterest pointOfInterest) {
        return pointOfInterestDao.update(pointOfInterest);
    }

    @Override
    public PointOfInterest deletePointOfInterest(long id) {
        PointOfInterest temp = pointOfInterestDao.getById(id);
        pointOfInterestDao.delete(temp);
        return temp;
    }
}
