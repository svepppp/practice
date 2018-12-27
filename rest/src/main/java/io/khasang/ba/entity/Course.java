package io.khasang.ba.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Course entity class. Provides remote consultations and study entity and in "Business Assistant" project.
 */
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @Column(name = "edition_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime editionDateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDateTime getEditionDateTime() {
        return editionDateTime;
    }

    public void setEditionDateTime(LocalDateTime editionDateTime) {
        this.editionDateTime = editionDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(name, course.name) &&
                Objects.equals(description, course.description) &&
                Objects.equals(editionDateTime, course.editionDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, editionDateTime);
    }
}
