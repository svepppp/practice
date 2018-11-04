package io.khasang.ba.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class DocumentItemMetadata {

    @Column(name = "file_name")
    private String fileName;

    private long size;

    @Column(name = "content_type")
    private String contentType;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime timestamp;

    public DocumentItemMetadata() {
    }

    public DocumentItemMetadata(MultipartFile multipartFile) {
        fileName = multipartFile.getOriginalFilename();
        size = multipartFile.getSize();
        contentType = multipartFile.getContentType();
        timestamp = LocalDateTime.now();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime creationDateTime) {
        this.timestamp = creationDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentItemMetadata that = (DocumentItemMetadata) o;
        return size == that.size &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(contentType, that.contentType) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, size, contentType, timestamp);
    }
}
