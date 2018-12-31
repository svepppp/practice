package io.khasang.ba.entity;

import javax.persistence.*;

/**
 * Request entity class. Saves data about a request in "Business Assistant" project.
 */
@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String category;
    private String description;

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
