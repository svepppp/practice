package io.khasang.ba.entity;

import javax.persistence.*;

/**
 * History entity class. Provides history in "Business Assistant" project.
 */
@Entity
@Table(name = "events")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
