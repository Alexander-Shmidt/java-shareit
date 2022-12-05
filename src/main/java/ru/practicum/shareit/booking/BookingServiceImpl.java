package ru.practicum.shareit.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.*;
import ru.practicum.shareit.item.ItemRepository;
import ru.practicum.shareit.user.UserRepository;

import java.time.LocalDateTime;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository,
                              ItemRepository itemRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }


    @Override
    public ResponseEntity<Booking> reserved(Long userId, BookingDto bookingDto) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Пользователь не найден");
        } else if (!itemRepository.existsById(bookingDto.getItemId())) {
            throw new ItemNotFoundException("Вещь не найдена в БД");
        } else if (!itemRepository.findById(bookingDto.getItemId()).get().getAvailable()) {
            throw new ItemNotContainsAvailibleException("Вещь не доступна для бронирования");
        } else if (bookingDto.getEnd().isBefore(bookingDto.getStart()) ||
                bookingDto.getStart().isBefore(LocalDateTime.now())) {
            throw new BookingStartOrEndErrorException("Неверные время начала или окончания бронирования");
        }
        Booking booking;
            booking = BookingMapper.toBooking(bookingDto);
            booking.setItem(itemRepository.findById(bookingDto.getItemId()).get());
            booking.setBooker(userRepository.findById(userId).get());
            booking.setStatus(Status.WAITING);
            bookingRepository.save(booking);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Booking> approvedBooking(Long userId, Long bookingId, String approved) {
        Boolean flag = Boolean.parseBoolean(approved);
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Пользователь не найден");
        } else if (!itemRepository.existsById(bookingRepository.findById(bookingId).get().getItem().getId())) {
            throw new ItemNotFoundException("Вещь не найдена в БД");
        } else if (userId.equals(itemRepository.findById(bookingRepository.findById(bookingId).get().getItem().getId()).
                get().getOwner())) {
            Booking booking;
            booking = bookingRepository.findById(bookingId).get();
            if (flag) {
                booking.setStatus(Status.APPROVED);
                bookingRepository.save(booking);
            } else {
                booking.setStatus(Status.REJECTED);
                bookingRepository.save(booking);
            }
        } else throw new ValidationException("Пользователь не является владельцем вещи");
        return new ResponseEntity<>(bookingRepository.findById(bookingId).get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Booking> bookingInfo(Long userId, Long bookingId) {
        if (!itemRepository.findById(bookingRepository.findById(bookingId).get().getItem().getId()).get().getOwner()
                .equals(userId) || !bookingRepository.findById(bookingId).get().getBooker().equals(userId)) {
            throw new ValidationException("У вас нет прав на этот запрос");
        }
        return new ResponseEntity<>(bookingRepository.findById(bookingId).get(), HttpStatus.OK);
    }
}
