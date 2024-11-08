package com.example.book_service_mono.service.userService;


import com.example.book_service_mono.domain.userDomain.User;
import com.example.book_service_mono.dto.userDto.UserBookListDto;
import com.example.book_service_mono.dto.userDto.UserCreationDto;
import com.example.book_service_mono.dto.userDto.UserDto;
import com.example.book_service_mono.dto.userDto.UserUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User findUserById(String id);

    UserDto findUserByFirstAndLastName(String firstName, String lastName);

    List<UserDto> findAllUsers();

    UserDto addUser(UserCreationDto userCreationDto);

    UserDto updateUser(String userId, UserUpdateDto userUpdateDto);

    void deleteUser(String userId);

    UserDto addBook(String userId, UserBookListDto userBookListDto);

    UserDto removeBook(String userId, UserBookListDto userBookListDto);
}
