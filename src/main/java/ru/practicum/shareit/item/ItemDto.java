package ru.practicum.shareit.item;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.transfer.New;

import javax.validation.constraints.*;

@Data
@Builder
public class ItemDto {

    @NotNull(groups = {New.class})
    @NotBlank(groups = {New.class})
    // @JsonView
    private String name; // краткое название

    @NotNull(groups = {New.class})
    @NotEmpty(groups = {New.class})
    // @JsonView
    private String description; // развёрнутое описание

    @NotNull(groups = {New.class})
    // @JsonView
    private Boolean available; // статус о том, доступна или нет вещь для аренды
}
