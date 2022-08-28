package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ItemNotFoundException;
import ru.practicum.shareit.exception.UserNotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemServise {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private long idItem = 0;

    public ItemServiceImpl(@Autowired ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void add(Long userId, ItemDto itemDto) {
        if (!userRepository.existsById(userId)) throw new UserNotFoundException("Владелец вещи не обнаружен");
        idItem = makeIdItem();
        Item item = ItemMapper.toItem(idItem, itemDto, userId);
        itemRepository.save(item);
    }

    @Override
    public Item findItemByItemId(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() ->
                new ItemNotFoundException("Вещь с таким идентификатором отсутствует"));
    }

    @Override
    public Long findItemByOwnerIdAndItemName(Long owner, ItemDto itemDto) {
        if (!itemRepository.findOwner(owner)) {
            throw new ValidationException("Владелец вещи не обнаружен");
        }
        for (Item item : itemRepository.findAll()) {
            if (item.getOwner() == owner && item.getName().equals(itemDto.getName())) {
                return item.getId();
            }
        }
        return -1L;
    }

    @Override
    public List<ItemDto> findItemsByOwner(long owner) {
        if (!itemRepository.findOwner(owner)) {
            throw new ValidationException("Владелец вещи не обнаружен");
        }
        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item item : itemRepository.findAll()) {
            if (item.getOwner() == owner) itemDtos.add(ItemMapper.toItemDto(item));
        }
        return itemDtos;
    }

    @Override
    public List<ItemDto> findTextByNameOrDescription(String text) {
        List<ItemDto> searchList = new ArrayList<>();
        if (text == null || text.isEmpty()) return searchList;
        for (Item item : itemRepository.findAll()) {
            if ((item.getName().toLowerCase().contains(text.toLowerCase()) ||
                    item.getDescription().toLowerCase().contains(text.toLowerCase())) && item.getAvailable()) {
                searchList.add(ItemMapper.toItemDto(item));
            }
        }
        return searchList;
    }

    @Override
    public void update(long userId, Long itemId, ItemDto itemDto) {
        if (!userRepository.existsById(userId)) throw new UserNotFoundException("Владелец вещи не обнаружен");
        if (!itemRepository.existsById(itemId)) {
            throw new UserNotFoundException("Вещь с таким идентификатором отсутствует");
        }
        Item item = itemRepository.findById(itemId).get();

        if (item.getOwner() == userId) {
            if (itemDto.getName() != null) item.setName(itemDto.getName());
            if (itemDto.getDescription() != null) item.setDescription(itemDto.getDescription());
            if (itemDto.getAvailable() != null) item.setAvailable(itemDto.getAvailable());
        } else throw new UserNotFoundException("Вещь не принадлежит этому владельцу");
        itemRepository.update(itemId, item);
    }


    private Long makeIdItem() {
        if (idItem == 0) {
            idItem = 1;
        } else {
            idItem++;
        }
        return idItem;
    }
}
