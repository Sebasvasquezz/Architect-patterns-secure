package escuelaing.edu.co.patrones.service;

import escuelaing.edu.co.patrones.model.Property;
import escuelaing.edu.co.patrones.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that provides business logic for managing Property entities.
 * This class interacts with the PropertyRepository to perform CRUD operations on Property objects.
 */
@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    /**
     * Creates a new Property and saves it to the database.
     *
     * @param property the Property object to be created.
     * @return the saved Property object.
     */
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    /**
     * Retrieves a list of all properties from the database.
     *
     * @return a list of all Property objects.
     */
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    /**
     * Retrieves a specific Property by its ID.
     *
     * @param id the ID of the Property to retrieve.
     * @return the Property object if found.
     * @throws RuntimeException if the Property is not found.
     */
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propiedad no encontrada"));
    }

    /**
     * Updates an existing Property with new details.
     *
     * @param id the ID of the Property to update.
     * @param propertyDetails the new details of the Property.
     * @return the updated Property object.
     * @throws RuntimeException if the Property is not found.
     */
    public Property updateProperty(Long id, Property propertyDetails) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propiedad no encontrada"));

        property.setAddress(propertyDetails.getAddress());
        property.setPrice(propertyDetails.getPrice());
        property.setSize(propertyDetails.getSize());
        property.setDescription(propertyDetails.getDescription());

        return propertyRepository.save(property);
    }

    /**
     * Deletes a specific Property by its ID.
     *
     * @param id the ID of the Property to delete.
     * @throws RuntimeException if the Property is not found.
     */
    public void deleteProperty(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propiedad no encontrada"));
        propertyRepository.delete(property);
    }
}

