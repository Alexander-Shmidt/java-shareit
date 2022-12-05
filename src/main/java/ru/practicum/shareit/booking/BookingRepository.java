package ru.practicum.shareit.booking;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import ru.practicum.shareit.item.Item;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findByBookerIdAndEndIsBefore(Long bookerId, LocalDateTime end, Sort sort);
}
