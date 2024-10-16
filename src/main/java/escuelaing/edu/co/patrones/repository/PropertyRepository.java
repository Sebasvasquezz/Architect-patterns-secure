package escuelaing.edu.co.patrones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import escuelaing.edu.co.patrones.model.Property;

/**
 * Repository interface for managing Property entities.
 * Extends JpaRepository to provide basic CRUD (Create, Read, Update, Delete) operations
 * and additional methods for interacting with the Property database table.
 * 
 * This interface is automatically implemented by Spring Data JPA, which provides
 * the necessary functionality for data access.
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}

