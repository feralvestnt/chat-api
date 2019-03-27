package br.com.chat.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@SequenceGenerator(name="USER_ID_SEQ", sequenceName="USER_ID_SEQ", allocationSize = 1)
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(generator = "USER_ID_SEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    public User() {}

    public User(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
