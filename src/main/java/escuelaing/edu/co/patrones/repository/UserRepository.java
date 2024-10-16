package escuelaing.edu.co.patrones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import escuelaing.edu.co.patrones.model.User;
import java.util.Optional;

/**
 * Repository interface for accessing and managing User entities in the database.
 * Extends JpaRepository to provide CRUD operations and additional query methods.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user to search for.
     * @return an Optional containing the User if found, or an empty Optional if no user is found.
     */
    Optional<User> findByUsername(String username);
}
