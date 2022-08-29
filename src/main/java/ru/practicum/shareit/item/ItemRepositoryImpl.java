package ru.practicum.shareit.item;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private final Map<Long, Item> items = new HashMap<>();

    @Override
    public <S extends Item> S save(S entity) {
        items.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Iterable<Item> findAll() {
        return items.values();
    }

    @Override
    public Optional<Item> findById(Long itemId) {
        return Optional.of(items.get(itemId));
    }

    @Override
    public boolean existsById(Long aLong) {
        return items.containsKey(aLong);
    }

    @Override
    public boolean findOwner(Long owner) {
        for (Item item : items.values()) {
            if (item.getOwner() == owner) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(Long itemId, Item item) {
        items.replace(itemId, item);
    }

}
