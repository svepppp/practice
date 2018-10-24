package io.khasang.ba.service.impl;

import io.khasang.ba.dao.PoiDao;
import io.khasang.ba.entity.Poi;
import io.khasang.ba.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoiServiceImpl implements PoiService {

    @Autowired
    private PoiDao poiDao;

    @Override
    public Poi addPoi(Poi poi) {
        poiDao.add(poi);
        return poi;
    }

    @Override
    public Poi getPoiById(long id) {
        return poiDao.getById(id);
    }

    @Override
    public List<Poi> getAllPoi() {
        return poiDao.getAll();
    }

    @Override
    public Poi updatePoi(Poi poi) {
        return poiDao.update(poi);
    }

    @Override
    public Poi deletePoi(long id) {
        Poi temp = poiDao.getById(id);
        poiDao.delete(temp);
        return temp;
    }
}
