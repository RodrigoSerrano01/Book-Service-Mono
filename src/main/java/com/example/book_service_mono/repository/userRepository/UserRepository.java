package com.example.book_service_mono.repository.userRepository;


import com.example.book_service_mono.domain.userDomain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface to manage User data
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Method that fetches User by first and last name.
     * @param firstName firstName
     * @param lastName lastName
     * @return User
     */
    Optional<User> findTopByFirstNameAndLastNameEqualsIgnoreCase(String firstName, String lastName);

    /**
     * Method that fetches the user found by its id.
     * @param id user's id
     * @return User
     */
    Optional<User> findUserById(String id);

    /**
     * Method that fetches the user found by its ssn.
     * @param ssn ssn
     * @return User
     */
    Optional<User> findTopBySsn(String ssn);
}
