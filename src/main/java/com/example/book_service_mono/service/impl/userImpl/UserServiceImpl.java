package com.example.book_service_mono.service.impl.userImpl;


import com.example.book_service_mono.domain.userDomain.User;
import com.example.book_service_mono.dto.mapping.userMapping.UserMapper;
import com.example.book_service_mono.dto.userDto.UserBookListDto;
import com.example.book_service_mono.dto.userDto.UserCreationDto;
import com.example.book_service_mono.dto.userDto.UserDto;
import com.example.book_service_mono.dto.userDto.UserUpdateDto;
import com.example.book_service_mono.exception.UserAlreadyExistsException;
import com.example.book_service_mono.exception.UserNotFoundException;
import com.example.book_service_mono.repository.userRepository.UserRepository;
import com.example.book_service_mono.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.book_service_mono.enumerator.Error.*;


/**
 * Implementation of the UserService interface, providing business logic for managing users.
 */
@Slf4j //  Annotation that generates a logger for the class.
@Service // Indicates that this class is a service component in Spring.
@RequiredArgsConstructor // Annotation that generates a constructor with all final fields.
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    /**
     * Finds and returns all users in the repository.
     * @return A list of userDro representing all users.
     * @throws UserNotFoundException If no users were found.
     */
    @Override
    public List<UserDto> findAllUsers() {
        log.info("Searching for users.");
        final List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            log.info(NO_USERS_FOUND.getErrorDescription());
            throw new UserNotFoundException(NO_USERS_FOUND.getErrorDescription());
        }
        return userMapper.userListToUserListDto(users);
    }

    /**
     * Finds and returns an user by its ID.
     * @param id The ID of the user.
     * @return The User object with the specified ID.
     * @throws UserNotFoundException If no user is found with the given ID.
     */
    @Override
    public User findUserById(String id) {
        log.info("Searching user by id {}", id);
        return userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException(NO_USER_FOUND_BY_ID.getErrorDescription()));
    }

    /**
     * Finds and returns an user by its first and last name.
     * @param firstName user first name.
     * @param lastName user last name.
     * @return The UserDto representing the user with the specified first and last name.
     * @throws UserNotFoundException If no user is found with the given params.
     */
    @Override
    public UserDto findUserByFirstAndLastName(String firstName, String lastName) {
        log.info("Searching user by first name {} and last name {}", firstName, lastName);
        final User user = userRepository.findTopByFirstNameAndLastNameEqualsIgnoreCase(firstName, lastName)
                .orElseThrow(() -> new UserNotFoundException(NO_USER_FOUND_BY_FIRST_AND_LAST_NAME.getErrorDescription()));
        return userMapper.userToUserDto(user);
    }

    /**
     * Adds a new user to the repository.
     * @param userCreationDto DTO containing the fields of the user to be added.
     * @return The UserDto representing the added user.
     * @throws UserAlreadyExistsException If an user with the same ssn already exists.
     */
    @Override
    public UserDto addUser(UserCreationDto userCreationDto) {
        log.info("Searching user by SSN {}", userCreationDto.getSsn());
        final Optional<User> userSearch = userRepository.findTopBySsn(userCreationDto.getSsn());
        if (userSearch.isPresent()) {
            log.error(USER_ALREADY_EXISTS.getErrorDescription());
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS.getErrorDescription());
        }
        final User user = userMapper.userCreationDtoToUser(userCreationDto);
        userRepository.save(user);
        log.info("User {} {} registered with success.", user.getFirstName(), user.getLastName());
        return userMapper.userToUserDto(user);
    }

    /**
     * Updates an existing user in the repository.
     * @param userId The ID of the user to be updated.
     * @param userUpdateDto DTO containing the updated fields of the user.
     * @return The UserDto representing the updated user.
     */
    @Override
    public UserDto updateUser(String userId, UserUpdateDto userUpdateDto) {
        final User existingUser = this.findUserById(userId);
        userMapper.userUpdateDtoToUser(userUpdateDto, existingUser);
        userRepository.save(existingUser);
        log.info("User {} {} updated with success.", existingUser.getFirstName(), existingUser.getLastName());
        return userMapper.userToUserDto(existingUser);
    }

    /**
     * Deletes an user from the repository.
     * @param userId The ID of the user to be deleted.
     */
    @Override
    public void deleteUser(String userId) {
        final User user = this.findUserById(userId);
        userRepository.deleteById(userId);
        log.info("User {} {} deleted.", user.getFirstName(), user.getLastName());
    }

    /**
     * Method to add booksIds to User
     * @param userId user Id
     * @param userBookListDto userBookListDto
     * @return The UserDto containing the now added books ids.
     */
    @Override
    public UserDto addBook(String userId, UserBookListDto userBookListDto) {
        final User user = this.findUserById(userId);
        final List<String> userBooks = user.getBooksIds();
        if (userBooks == null) {
            user.setBooksIds(new ArrayList<>());
        }
        user.getBooksIds().addAll(userBookListDto.getBooksIds());
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    /**
     * Method to remove booksIds from User
     * @param userId user Id
     * @param userBookListDto userBookListDto
     * @return The UserDto.
     */
    @Override
    public UserDto removeBook(String userId, UserBookListDto userBookListDto) {
        final User user = this.findUserById(userId);
        user.getBooksIds().removeAll(userBookListDto.getBooksIds());
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }
}
