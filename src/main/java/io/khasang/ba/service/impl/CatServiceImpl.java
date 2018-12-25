package io.khasang.ba.service.impl;

import io.khasang.ba.dao.CatDao;
import io.khasang.ba.entity.Cat;
import io.khasang.ba.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl implements CatService {
    @Autowired
    private CatDao catDao;

    @Override
    public Cat addCat(Cat cat) {
        return catDao.add(cat);
    }

    @Override
    public Cat getCatById(long id) {
        return catDao.getById(id);
    }

    @Override
    public List<Cat> getAllCats() {
        return catDao.getAll();
    }

    @Override
    public Cat updateCat(Cat cat) {
        return catDao.update(cat);
    }

    @Override
    public Cat deleteCat(long id) {
        return catDao.delete(getCatById(id));
    }
}
