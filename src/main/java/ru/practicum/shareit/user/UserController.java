package ru.practicum.shareit.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.transfer.New;
import ru.practicum.shareit.transfer.UpdateName;
import ru.practicum.shareit.validators.Validator;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody @Validated(New.class) UserDto userDto) {
        userService.save(userDto);
        log.debug("Добавлена запись о пользователе {}", userDto.getName());
        return ResponseEntity.ok(userService.findUserByName(userDto.getName()));
    }

   @PutMapping
    public ResponseEntity addOrUpdateUser(@Validated(UpdateName.class) @RequestBody UserDto userDto) {
       log.debug("Изменена запись о пользователе {}", userDto.getName());
        userService.update(userDto);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable long id) {

        return ResponseEntity.ok(userService.getUser(id));
    }

    @DeleteMapping("/{userId}")
    public  HttpStatus deleteUser(@PathVariable long userId){
        userService.deleteUser(userId);
        return HttpStatus.OK;
    }

    @PatchMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable long userId,
                                     @RequestBody UserDto userDto) {
        try {
            Validator.updateUserValidation(userDto);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e);
        }
        log.debug("Обновлена запись о пользователе {}", userDto.getName());
        userService.update(userDto, userId);
        return ResponseEntity.ok(userService.getUser(userId));
    }
}
