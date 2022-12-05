package ru.practicum.shareit.item;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@Table(name = "items")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // уникальный идентификатор вещи

    @Column(nullable = false)
    private String name; // краткое название

    @Column(nullable = false)
    private String description; // развёрнутое описание

    @Column(name = "is_available", nullable = false)
    private Boolean available; // статус о том, доступна или нет вещь для аренды

    @Column(name = "owner_id", nullable = false)
    private Long owner; // владелец вещи

    @Column(name = "request_id")
    private Long idRequest; // — если вещь была создана по запросу другого пользователя, то в этом
    // поле будет храниться ссылка на соответствующий запрос


    public Item(Long id, String name, String description, Boolean available, Long owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.owner = owner;
    }

    public Item() {
    }

    public Item(String name, String description, boolean available, long owner) {
        this.name = name;
        this.description = description;
        this.available = available;
        this.owner = owner;

    }
}
