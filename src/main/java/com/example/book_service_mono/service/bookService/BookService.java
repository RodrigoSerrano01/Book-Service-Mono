package com.example.book_service_mono.service.bookService;


import com.example.book_service_mono.domain.bookDomain.Book;
import com.example.book_service_mono.dto.bookDto.BookCreationDto;
import com.example.book_service_mono.dto.bookDto.BookDto;
import com.example.book_service_mono.dto.bookDto.BookUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    Book findBookById(String id);

    BookDto findBookByTitle(String title);

    List<BookDto> findAllBooks();

    BookDto addBook(BookCreationDto bookCreationDto);

    BookDto updateBook(String bookId, BookUpdateDto bookUpdateDto);

    void deleteBook(String bookId);
    void bookPurchase(String bookId);
}
