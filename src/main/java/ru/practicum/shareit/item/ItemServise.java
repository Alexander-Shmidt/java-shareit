package ru.practicum.shareit.item;

import java.util.List;

public interface ItemServise {

    void add(Long userId, ItemDto itemDto);

    Item findItemByItemId(Long itemId);

    Long findItemByOwnerIdAndItemName(Long owner, ItemDto itemDto);

    void update(long userId, Long itemId, ItemDto itemDto);

    List<Item> findItemsByOwnerOnly(long userId);

    List<Item> findTextByNameOrDescription(String text);
}
