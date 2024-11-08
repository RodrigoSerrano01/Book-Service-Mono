package com.example.book_service_mono.repository.bookRepository;


import com.example.book_service_mono.domain.bookDomain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface to manage Book data
 */
@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    /**
     * Method that fetches the first book found by its title
     * @param title book's title
     * @return Book
     */
    Optional<Book> findTopByTitleEqualsIgnoreCase(String title);

    /**
     * Method that fetches the book found by its id
     * @param id book's id
     * @return Book
     */
    Optional<Book> findBookById(String id);
}
