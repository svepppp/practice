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
}
