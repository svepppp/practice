package io.khasang.ba.service.impl;

import io.khasang.ba.dao.OnlineQueueDao;
import io.khasang.ba.entity.OnlineQueue;
import io.khasang.ba.service.OnlineQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnlineQueueServiceImpl implements OnlineQueueService {

    @Autowired
    private OnlineQueueDao onlineQueueDao;

    @Override
    public OnlineQueue addOnlineQueue(OnlineQueue onlineQueue) {
        return onlineQueueDao.add(onlineQueue);
    }

    @Override
    public OnlineQueue getOnlineQueueById(long id) {
        return onlineQueueDao.getById(id);
    }

    @Override
    public List<OnlineQueue> getAllOnlineQueue() {
        return onlineQueueDao.getAll();
    }

    @Override
    public OnlineQueue updateOnlineQueue(OnlineQueue onlineQueue) {
        return onlineQueueDao.update(onlineQueue);
    }

    @Override
    public OnlineQueue deleteOnlineQueue(long id) {
        return onlineQueueDao.delete(getOnlineQueueById(id));
    }
}
