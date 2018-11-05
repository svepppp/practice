package io.khasang.ba.entity;

import javax.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*
        feedback name
     */
    private String title;

    private String clientMessage;
    private String adminAnswer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClientMessage() {
        return clientMessage;
    }

    public void setClientMessage(String clientMessage) {
        this.clientMessage = clientMessage;
    }

    public String getAdminAnswer() {
        return adminAnswer;
    }

    public void setAdminAnswer(String adminAnswer) {
        this.adminAnswer = adminAnswer;
    }
}
