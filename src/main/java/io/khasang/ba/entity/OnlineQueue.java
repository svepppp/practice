package io.khasang.ba.entity;

import javax.persistence.*;

/**
 * Online queue for registration of requests for services
 */
@Entity
@Table(name = "online_queue")
public class OnlineQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Queue name
     */
    private String title;

    /**
     * Service with which the queue is connected
     */
    private String service;

    /**
     * Service requests
     */
    private String  requests;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRequests() {
        return requests;
    }

    public void setRequests(String requests) {
        this.requests = requests;
    }
}
