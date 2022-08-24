package ru.practicum.shareit.user;

import java.io.Serializable;
import java.util.Objects;

// @Entity
public class User implements Serializable {

    // @Id
    // @Null(groups = {New.class})
    // @NotNull(groups = {UpdateName.class})
    private Long id; // уникальный идентификатор пользователя

    // @NotBlank(groups = {New.class}, message = "Имя/логин не могут быть пустыми")
    // @NotNull(groups = {New.class}, message = "Это поле должно быть заполнено")
    // @Null(groups = {UpdateName.class})
    private String name; // имя или логин пользователя

    // @NotNull(groups = {New.class})
    // @Email(groups = {New.class}, message = "Проверьте корректность ввода e-mail")
    // @Null(groups = {UpdateName.class})
    private String email; // адрес электронной почты


    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User() {

    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && name.equals(user.name) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
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
