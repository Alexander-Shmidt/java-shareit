package ru.practicum.shareit.booking;

import lombok.Data;
import ru.practicum.shareit.Status;

import java.util.Date;

@Data
public class Booking {
    private long id; // уникальный идентификатор бронирования
    private Date start; // дата начала бронирования
    private Date end; // дата конца бронирования
    private long item; // вещь, которую пользователь бронирует
    private long booker; // пользователь, который осуществляет бронирование
    private Status status; // статус бронирования. Может принимать одно из следующих значений:
    // WAITING — новое бронирование, ожидает одобрения,
    // APPROVED — бронирование подтверждено владельцем,
    // REJECTED — бронирование отклонено владельцем,
    // CANCELED — бронирование отменено создателем
}
