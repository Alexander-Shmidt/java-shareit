package ru.practicum.shareit.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.validators.Validator;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/items")
public class ItemController {
    private final Validator validator = new Validator();
    private final ItemServise itemServise;


    public ItemController(@Autowired ItemServise itemServise) {
        this.itemServise = itemServise;
    }


    @GetMapping // Просмотр всех вещей только их владельцем
    public List<ItemDto> viewAllByOwner(@RequestHeader("X-Sharer-User-Id") long userId) {

        return itemServise.findItemsByOwner(userId);
    }

    @PostMapping
    public ResponseEntity addItem(@RequestHeader("X-Sharer-User-Id") long userId,
                                  @RequestBody ItemDto itemDto) {
        try {
            validator.addItemValidation(itemDto);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e);
        }
        log.debug("Добавлена запись о вещи {}", itemDto.getName());
        itemServise.add(userId, itemDto);
        return ResponseEntity.ok(itemServise.findItemByItemId(itemServise.findItemByOwnerIdAndItemName(userId, itemDto)));
    }

    @PatchMapping("/{itemId}") // Редактирование вещи. Доступно только для владельца вещи
    public ResponseEntity updateItem(@RequestHeader("X-Sharer-User-Id") long userId,
                                     @PathVariable Long itemId, @RequestBody ItemDto itemDto) {
        log.debug("Изменена запись о вещи {}", itemDto.getName());
        itemServise.update(userId, itemId, itemDto);
        return ResponseEntity.ok(itemServise.findItemByItemId(itemId));
    }

    @GetMapping("/{itemId}") // Просмотр информации о вещи. Доступно всем пользователям
    public ResponseEntity viewItem(@PathVariable Long itemId) {
        return ResponseEntity.ok(ItemMapper.toItemDto(itemServise.findItemByItemId(itemId)));
    }

    @GetMapping("/search") // Текст для поиска ищется в названии или описании
    public List<ItemDto> searchItems(@RequestParam String text) {

        return itemServise.findTextByNameOrDescription(text);
    }

}
