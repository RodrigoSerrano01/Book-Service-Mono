package com.example.book_service_mono.dto.mapping.userMapping;



import com.example.book_service_mono.domain.userDomain.User;
import com.example.book_service_mono.dto.userDto.UserCreationDto;
import com.example.book_service_mono.dto.userDto.UserDto;
import com.example.book_service_mono.dto.userDto.UserUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Mapper interface for converting between domain objects and DTOs using MapStruct.
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {

    User userCreationDtoToUser(UserCreationDto userCreationDto);

    UserDto userToUserDto(User user);

    List<UserDto> userListToUserListDto(List<User> users);

    void userUpdateDtoToUser(UserUpdateDto userUpdateDto, @MappingTarget User user);
}
