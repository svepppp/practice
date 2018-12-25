package io.khasang.ba.entity;
/**
 * Ticket entity
 *
 * @param id - ticket id from DB
 * @param dateOfIssue - ticket's date of issue
 * @param series - direction to the department
 */

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ticket_id")
    private long id;

    @Column(columnDefinition = "DATE")
    private LocalDate dateOfIssue;
    private String series;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
}