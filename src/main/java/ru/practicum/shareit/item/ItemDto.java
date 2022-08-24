package ru.practicum.shareit.item;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {
    private long id; // уникальный идентификатор вещи
    // @NotNull
    // @JsonView
    private String name; // краткое название

    // @JsonView
    private String description; // развёрнутое описание
    // @JsonView
    private Boolean available; // статус о том, доступна или нет вещь для аренды
    // private long owner; // владелец вещи

    // private long request; // — если вещь была создана по запросу другого пользователя, то в этом
    // поле будет храниться ссылка на соответствующий запрос
}
