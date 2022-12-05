package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ru.practicum.shareit.item.Item;
import ru.practicum.shareit.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // уникальный идентификатор бронирования

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "YYYY-MM-DDTHH:mm:ss")
    private LocalDateTime start; // дата начала бронирования

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "YYYY-MM-DDTHH:mm:ss")
    private LocalDateTime end; // дата конца бронирования

    @ManyToOne(fetch = FetchType.LAZY)
    @CollectionTable(name = "items", joinColumns = @JoinColumn(name = "id"))
    private Item item; // вещь, которую пользователь бронирует

    @OneToOne
    @CollectionTable(name = "users", joinColumns = @JoinColumn(name = "id"))
    private User booker; // пользователь, который осуществляет бронирование

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status; // статус бронирования. Может принимать одно из следующих значений:
    // WAITING — новое бронирование, ожидает одобрения,
    // APPROVED — бронирование подтверждено владельцем,
    // REJECTED — бронирование отклонено владельцем,
    // CANCELED — бронирование отменено создателем
}
