package ru.practicum.shareit.user;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.UserNotFoundException;
import ru.practicum.shareit.exception.ValidationException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiseImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Iterable<User> getAllUsers() {
        return  userRepository.findAll();
    }

    @Override
    public void save(UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Пользователя с таким ID не существует");
        }
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("Пользователь не найден"));
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Пользователя с таким ID не существует");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public void update(UserDto userDto) {
        User user = new User();
        for (User user1 : userRepository.findAll()) {
            if (user1.getName().equals(userDto.getName()) || user1.getEmail().equals(userDto.getEmail())) {
                user.setId(user1.getId());
                user.setName(userDto.getName());
                user.setEmail(userDto.getEmail());
            }
        }
        userRepository.save(user);
    }

    @Override
    public void update(UserDto userDto, long userId) {
        User user;
        if (userRepository.existsById(userId)) {
            user = userRepository.findById(userId).get();
            if (!(userDto.getEmail() == null || userDto.getEmail().isBlank())) {
                for (User user1 : userRepository.findAll()) {
                    if (user1.getEmail().equals(userDto.getEmail())) {
                        throw new ValidationException("Пользователь с такой почтой уже зарегистрирован");
                    }
                }
                user.setEmail(userDto.getEmail());
            }
            if (!(userDto.getName() == null || userDto.getName().isBlank())) {
                user.setName(userDto.getName());
            }
            userRepository.save(user);
        } else throw new UserNotFoundException("Пользователь с таким Id не найден");
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return userRepository.findUserByName(name);
    }
}
