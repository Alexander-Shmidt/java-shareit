package ru.practicum.shareit.item;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class Item implements Serializable {
    private Long id; // уникальный идентификатор вещи
    private String name; // краткое название
    private String description; // развёрнутое описание
    private Boolean available; // статус о том, доступна или нет вещь для аренды
    private long owner; // владелец вещи
    private Long idRequest; // — если вещь была создана по запросу другого пользователя, то в этом
    // поле будет храниться ссылка на соответствующий запрос


    public Item(Long id, String name, String description, boolean available, long owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.owner = owner;
    }

    public Item() {
    }

    public Item(Long id, String name, String description, boolean available, long owner, Long idRequest) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.owner = owner;
        this.idRequest = idRequest;
    }
}
