package com.example.book_service_mono.controller.userController;

import com.example.book_service_mono.controller.RootController;
import com.example.book_service_mono.domain.userDomain.User;
import com.example.book_service_mono.dto.mapping.userMapping.UserMapperImpl;
import com.example.book_service_mono.dto.userDto.UserBookListDto;
import com.example.book_service_mono.dto.userDto.UserCreationDto;
import com.example.book_service_mono.dto.userDto.UserDto;
import com.example.book_service_mono.dto.userDto.UserUpdateDto;
import com.example.book_service_mono.service.impl.userImpl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class where User data will be created,
 * read, updated and deleted (CRUD) through User-Service APIs.
 */
@RestController // Annotation indicating that this class is a REST controller.
@RequiredArgsConstructor // Generates a constructor with all final fields for dependency injection.
public class UserController extends RootController {

    private final UserServiceImpl userServiceImpl;
    private final UserMapperImpl userMapperImpl;

    /**
     * Endpoint to fetch all users.
     * @return List of all users.
     */
    @GetMapping(path = "/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> fetchAllUsers() {
        return userServiceImpl.findAllUsers();
    }

    /**
     * Endpoint to fetch user by its first and last name.
     * @param firstName user first name.
     * @param lastName user last name.
     * @return The user with given first and last name.
     */
    @GetMapping(path = "/users/user")
    @ResponseStatus(HttpStatus.OK)
    public UserDto fetchUserByFirstAndLastName(@RequestParam String firstName,
                                               @RequestParam String lastName) {
        return userServiceImpl.findUserByFirstAndLastName(firstName, lastName);
    }

    @GetMapping(path = "/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto fetchUserById(@PathVariable String userId) {
        User user =  userServiceImpl.findUserById(userId);
        return userMapperImpl.userToUserDto(user);
    }

    /**
     * Endpoint to add a new user.
     * @param userCreationDto DTO containing the fields of the user to be added.
     * @return The added user.
     */
    @PostMapping(path = "/users/user.add")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@Valid @RequestBody UserCreationDto userCreationDto) {
        return userServiceImpl.addUser(userCreationDto);
    }

    /**
     * Endpoint to update an existing user.
     * @param userId The ID of the user to update.
     * @param userUpdateDto DTO containing the updated fields of the user.
     * @return The updated user.
     */
    @PostMapping(path = "/users/{userId}/user.update")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@PathVariable String userId, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        return userServiceImpl.updateUser(userId, userUpdateDto);
    }

    /**
     * Endpoint to delete am user.
     * @param userId The ID of the user to delete.
     */
    @DeleteMapping(path = "/users/{userId}/user.delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String userId) {
        userServiceImpl.deleteUser(userId);
    }

    /**
     * Endpoint to add booksIds to User.
     * @param userId The ID of the user.
     * @param userBookListDto user books ids list to add to User
     * @return UserDto
     */
    @PostMapping(path = "/users/{userId}/user.books.add")
    @ResponseStatus(HttpStatus.OK)
    public UserDto addUserBooks(@PathVariable String userId, @Valid @RequestBody UserBookListDto userBookListDto) {
        return userServiceImpl.addBook(userId, userBookListDto);
    }

    /**
     * Endpoint to remove booksIds from User.
     * @param userId The ID of the user.
     * @param userBookListDto user books ids list to remove from User
     * @return UserDto
     */
    @PostMapping(path = "/users/{userId}/user.books.remove")
    @ResponseStatus(HttpStatus.OK)
    public UserDto removeUserBooks(@PathVariable String userId, @Valid @RequestBody UserBookListDto userBookListDto) {
        return userServiceImpl.removeBook(userId, userBookListDto);
    }
}
