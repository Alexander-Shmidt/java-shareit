package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // уникальный идентификатор пользователя

    @Column(name = "name", nullable = false)
    private String name; // имя или логин пользователя

    @Column(name = "email", unique = true, nullable = false)
    private String email; // адрес электронной почты


    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
