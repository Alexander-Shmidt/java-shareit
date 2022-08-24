package ru.practicum.shareit.requests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

// @Entity
public class ItemRequest {
    private long id; // уникальный идентификатор запроса
    private String description; // текст запроса, содержащий описание требуемой вещи
    private long requestor; // пользователь, создавший запрос
    private LocalDateTime created; // дата и время создания запроса

    // @Id
    // @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getRequestor() {
        return requestor;
    }

    public void setRequestor(long requestor) {
        this.requestor = requestor;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
