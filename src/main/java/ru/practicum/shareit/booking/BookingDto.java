package ru.practicum.shareit.booking;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.practicum.shareit.item.Item;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    @NotNull
    private Long id;
    @DateTimeFormat(pattern = "YYYY-MM-DDTHH:mm:ss")
    private LocalDateTime start; // дата начала бронирования
    @DateTimeFormat(pattern = "YYYY-MM-DDTHH:mm:ss")
    private LocalDateTime end; // дата конца бронирования
    private Long itemId; // вещь, которую пользователь бронирует
    private Long bookerId; // пользователь, который осуществляет бронирование
    private Status status; // статус бронирования. Может принимать одно из следующих значений: см. Booking
}
