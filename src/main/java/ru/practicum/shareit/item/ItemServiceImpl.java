package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.BookingRepository;
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
    private final BookingRepository bookingRepository;

    public ItemServiceImpl(@Autowired ItemRepository itemRepository, UserRepository userRepository,
                           BookingRepository bookingRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void add(Long userId, ItemDto itemDto) {
        if (userRepository.existsById(userId)){
            Item item = ItemMapper.toItem(itemDto, userId);
            itemRepository.save(item);
        }

    }

    @Override
    public Item findItemByItemId(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() ->
                new ItemNotFoundException("Вещь с таким идентификатором отсутствует"));
    }

    @Override
    public Long findItemByOwnerIdAndItemName(Long owner, ItemDto itemDto) {
        if (itemRepository.findItemsByOwner(owner).isEmpty()) {
            throw new ValidationException("Владелец вещи не обнаружен");
        }
        for (Item item : itemRepository.findItemsByOwner(owner)) {
            if (item.getName().equals(itemDto.getName())) {
                return item.getId();
            }
        }
        return -1L;
    }

    @Override
    public List<Item> findItemsByOwnerOnly(long owner) {
        if (itemRepository.findItemsByOwner(owner).isEmpty()) {
            throw new ValidationException("Владелец вещи не обнаружен");
        }
        return itemRepository.findItemsByOwner(owner);
    }

    @Override
    public List<Item> findTextByNameOrDescription(String text) {
        List<Item> searchList = new ArrayList<>();
        if (text == null || text.isEmpty()) return searchList;
        for (Item item : itemRepository.findAll()) {
            if ((item.getName().toLowerCase().contains(text.toLowerCase()) ||
                    item.getDescription().toLowerCase().contains(text.toLowerCase())) && item.getAvailable()) {
                searchList.add(item);
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
        itemRepository.save(item);
    }
}
