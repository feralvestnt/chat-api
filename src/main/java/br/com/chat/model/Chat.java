package br.com.chat.model;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@SequenceGenerator(name="CHAT_ID_SEQ", sequenceName="CHAT_ID_SEQ", allocationSize = 1)
@Table(name = "CHAT")
public class Chat {

    @Id
    @GeneratedValue(generator = "CHAT_ID_SEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TEXT")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USER", nullable = false, foreignKey = @ForeignKey(name = "FK_USER"))
    private User user;

    public Chat() {}

    public Chat(Integer id, String text, User user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }

}
