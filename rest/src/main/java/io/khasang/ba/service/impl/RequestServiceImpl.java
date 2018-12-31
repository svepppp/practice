package io.khasang.ba.service.impl;

import io.khasang.ba.dao.RequestDao;
import io.khasang.ba.entity.Request;
import io.khasang.ba.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestDao requestDao;

    @Override
    public Request addRequest(Request request) {
        return requestDao.add(request);
    }

    @Override
    public Request getRequestById(long id) {
        return requestDao.getById(id);
    }

    @Override
    public List<Request> getAllRequests() {
        return requestDao.getAll();
    }

    @Override
    public Request updateRequest(Request Request) {
        return requestDao.update(Request);
    }

    @Override
    public Request deleteRequest(long id) {
        return requestDao.delete(getRequestById(id));
    }
}
