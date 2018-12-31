package io.khasang.ba.service;

import io.khasang.ba.entity.Request;

import java.util.List;

public interface RequestService {
    /**
     * method for add request
     *
     * @param request = request for adding
     * @return created Request
     */
    Request addRequest(Request request);

    /**
     * method for getting request by specific id
     *
     * @param id - request's id
     * @return request by id
     */
    Request getRequestById(long id);

    /**
     * method for getting all requests
     *
     * @return all requests
     */
    List<Request> getAllRequests();

    /**
     * method for update request
     *
     * @param request - request's with updated params
     * @return updated request
     */
    Request updateRequest(Request request);

    /**
     * method for delete request by id
     *
     * @param id - request's id for delete
     * @return deleted request
     */
    Request deleteRequest(long id);
}
