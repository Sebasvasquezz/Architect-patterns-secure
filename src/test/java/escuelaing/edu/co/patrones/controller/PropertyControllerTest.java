package escuelaing.edu.co.patrones.controller;

import escuelaing.edu.co.patrones.model.Property;
import escuelaing.edu.co.patrones.service.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PropertyControllerTest {

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private PropertyController propertyController;

    private Property property;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        property = new Property();
        property.setId(1L);
        property.setAddress("123 Main St");
        property.setPrice(250000.0);
        property.setSize(200.0);
        property.setDescription("A beautiful home.");
    }

    @Test
    void testCreateProperty() {
        when(propertyService.createProperty(property)).thenReturn(property);

        Property createdProperty = propertyController.createProperty(property);

        assertEquals(property, createdProperty);
        verify(propertyService, times(1)).createProperty(property);
    }

    @Test
    void testGetAllProperties() {
        List<Property> properties = Arrays.asList(property);
        when(propertyService.getAllProperties()).thenReturn(properties);

        List<Property> result = propertyController.getAllProperties();

        assertEquals(1, result.size());
        assertEquals(property, result.get(0));
        verify(propertyService, times(1)).getAllProperties();
    }

    @Test
    void testGetPropertyById() {
        when(propertyService.getPropertyById(1L)).thenReturn(property);

        ResponseEntity<Property> response = propertyController.getPropertyById(1L);

        assertEquals(property, response.getBody());
        verify(propertyService, times(1)).getPropertyById(1L);
    }

    @Test
    void testUpdateProperty() {
        when(propertyService.updateProperty(1L, property)).thenReturn(property);

        ResponseEntity<Property> response = propertyController.updateProperty(1L, property);

        assertEquals(property, response.getBody());
        verify(propertyService, times(1)).updateProperty(1L, property);
    }

    @Test
    void testDeleteProperty() {
        doNothing().when(propertyService).deleteProperty(1L);

        ResponseEntity<?> response = propertyController.deleteProperty(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(propertyService, times(1)).deleteProperty(1L);
    }
}
