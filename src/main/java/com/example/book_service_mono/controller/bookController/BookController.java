package com.example.book_service_mono.controller.bookController;


import com.example.book_service_mono.controller.RootController;
import com.example.book_service_mono.domain.bookDomain.Book;
import com.example.book_service_mono.dto.bookDto.BookCreationDto;
import com.example.book_service_mono.dto.bookDto.BookDto;
import com.example.book_service_mono.dto.bookDto.BookUpdateDto;
import com.example.book_service_mono.dto.mapping.bookMapping.BookMapper;
import com.example.book_service_mono.service.impl.bookImpl.BookServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class where Book data will be created,
 * read, updated and deleted (CRUD) through Book-Service APIs.
 */
@RestController // Annotation indicating that this class is a REST controller.
@RequiredArgsConstructor // Generates a constructor with all final fields for dependency injection.
public class BookController extends RootController {

    private final BookServiceImpl bookServiceImpl;
    private final BookMapper bookMapper;

    /**
     * Endpoint to fetch all books.
     * @return List of all books.
     */
    @GetMapping(path = "/books")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> fetchAllBooks() {
        return bookServiceImpl.findAllBooks();
    }

    /**
     * Endpoint to fetch a book by its title.
     * @param title The title of the book to fetch.
     * @return The book with the specified title.
     */
    @GetMapping(path = "/books/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public BookDto fetchBookByTitle(@Valid @PathVariable String title) {
        return bookServiceImpl.findBookByTitle(title);
    }

    /**
     * Endpoint to fetch a book by its id.
     * @param bookId The id of the book to fetch.
     * @return The book with the specified id.
     */
    @GetMapping(path = "/books/id/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public BookDto fetchBookById(@Valid @PathVariable String bookId) {
        final Book book = bookServiceImpl.findBookById(bookId);
        return bookMapper.bookToBookDto(book);
    }

    /**
     * Endpoint to add a new book.
     * @param bookCreationDto DTO containing the fields of the book to be added.
     * @return The added book.
     */
    @PostMapping(path = "/books/book.add")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto addBook(@Valid @RequestBody BookCreationDto bookCreationDto) {
        return bookServiceImpl.addBook(bookCreationDto);
    }

    /**
     * Endpoint to update an existing book.
     * @param bookId The ID of the book to update.
     * @param bookUpdateDto DTO containing the updated fields of the book.
     * @return The updated book.
     */
    @PostMapping(path = "/books/{bookId}/book.update")
    @ResponseStatus(HttpStatus.OK)
    public BookDto updateBook(@PathVariable String bookId, @Valid @RequestBody BookUpdateDto bookUpdateDto) {
        return bookServiceImpl.updateBook(bookId, bookUpdateDto);
    }

    /**
     * Endpoint to manage book purchasing logic.
     * @param bookId The ID of the book to be purchased.
     * @return The book with current quantity.
     */
    @PostMapping(path = "/books/book.purchase")
    @ResponseStatus(HttpStatus.OK)
    public void bookPurchase(@RequestBody String bookId) {
        bookServiceImpl.bookPurchase(bookId);
    }

    /**
     * Endpoint to delete a book.
     * @param bookId The ID of the book to delete.
     */
    @DeleteMapping(path = "/books/{bookId}/book.delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String bookId) {
        bookServiceImpl.deleteBook(bookId);
    }
}
