package com.example.book_service_mono.dto.mapping.bookMapping;


import com.example.book_service_mono.domain.bookDomain.Book;
import com.example.book_service_mono.dto.bookDto.BookCreationDto;
import com.example.book_service_mono.dto.bookDto.BookDto;
import com.example.book_service_mono.dto.bookDto.BookUpdateDto;
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
public interface BookMapper {

    Book bookCreationDtoToBook(BookCreationDto bookCreationDto);

    BookDto bookToBookDto(Book book);

    List<BookDto> bookListToBookListDto(List<Book> books);

    void bookUpdateDtoToBook(BookUpdateDto bookUpdateDto, @MappingTarget Book book);
}
