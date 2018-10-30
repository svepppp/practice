package io.khasang.ba.service;

import io.khasang.ba.entity.OnlineQueue;

import java.util.List;

public interface OnlineQueueService {

    /**
     * Method for add OnlineQueue
     *
     * @param onlineQueue - onlineQueue for add
     * @return created onlineQueue
     */
    OnlineQueue addOnlineQueue(OnlineQueue onlineQueue);

    /**
     * Method for getting OnlineQueue by specific id
     *
     * @param id - onlineQueue's id
     * @return onlineQueue by id
     */
    OnlineQueue getOnlineQueueById(long id);

    /**
     * Method for getting all OnlineQueue
     *
     * @return all onlineQueues
     */
    List<OnlineQueue> getAllOnlineQueue();

    /**
     * Method for update OnlineQueue
     *
     * @param onlineQueue - onlineQueue with updated params
     * @return updated onlineQueue
     */
    OnlineQueue updateOnlineQueue(OnlineQueue onlineQueue);

    /**
     * Method for delete OnlineQueue by id
     *
     * @param id - onlineQueue's id for delete
     * @return deleted onlineQueue
     */
    OnlineQueue deleteOnlineQueue(long id);
}
