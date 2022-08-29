package ru.practicum.shareit.item;

public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable()
        );
    }

    public static Item toItem(long idItem, ItemDto itemDto, Long userId) {
        return new Item(
                idItem,
                itemDto.getName(),
                itemDto.getDescription(),
                itemDto.getAvailable(),
                userId
        );
    }
}
