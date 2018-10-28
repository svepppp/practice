package io.khasang.ba.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "document_items")
public class DocumentItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private DocumentItemMetadata metadata;

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

    public DocumentItemMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(DocumentItemMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentItem that = (DocumentItem) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, metadata);
    }
}
