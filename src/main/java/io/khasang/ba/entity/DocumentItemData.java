package io.khasang.ba.entity;

import javax.persistence.*;

@Entity
@Table(name = "document_items_data")
public class DocumentItemData {

    @Id
    private Long id;

    private byte[] data;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private DocumentItem documentItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] bytes) {
        this.data = bytes;
    }

    public DocumentItem getDocumentItem() {
        return documentItem;
    }

    public void setDocumentItem(DocumentItem documentItem) {
        this.documentItem = documentItem;
    }
}
