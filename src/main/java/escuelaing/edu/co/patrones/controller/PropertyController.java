package escuelaing.edu.co.patrones.controller;


import escuelaing.edu.co.patrones.model.Property;
import escuelaing.edu.co.patrones.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller that handles HTTP requests for managing properties.
 * Provides CRUD operations (Create, Read, Update, Delete) for the Property entity.
 */
@CrossOrigin(origins = "https://localhost:8443")
@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    /**
     * Creates a new property.
     * 
     * @param property the Property object to be created, passed in the request body.
     * @return the newly created Property object.
     */
    @PostMapping
    public Property createProperty(@RequestBody Property property) {
        return propertyService.createProperty(property);
    }

    /**
     * Retrieves a list of all properties.
     * 
     * @return a list of Property objects.
     */
    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    /**
     * Retrieves a specific property by its ID.
     * 
     * @param id the ID of the Property to retrieve, passed as a path variable.
     * @return a ResponseEntity containing the Property object if found, or a 404 status if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Property property = propertyService.getPropertyById(id);
        return ResponseEntity.ok(property);
    }

    /**
     * Updates an existing property.
     * 
     * @param id the ID of the Property to update, passed as a path variable.
     * @param propertyDetails the updated Property object, passed in the request body.
     * @return a ResponseEntity containing the updated Property object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property propertyDetails) {
        Property updatedProperty = propertyService.updateProperty(id, propertyDetails);
        return ResponseEntity.ok(updatedProperty);
    }

    /**
     * Deletes a property by its ID.
     * 
     * @param id the ID of the Property to delete, passed as a path variable.
     * @return a ResponseEntity with a 200 OK status once the Property has been successfully deleted.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.ok().build();
    }
}

