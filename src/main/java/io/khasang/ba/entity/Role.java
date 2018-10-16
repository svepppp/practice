package io.khasang.ba.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * Role entity class. Provides spring security roles in "Business Assistant" project.
 * Each role must have unique name
 */
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NaturalId
    private String name;

    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
