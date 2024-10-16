package escuelaing.edu.co.patrones.service;

import escuelaing.edu.co.patrones.model.Property;
import escuelaing.edu.co.patrones.repository.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyService propertyService;

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
        when(propertyRepository.save(property)).thenReturn(property);

        Property createdProperty = propertyService.createProperty(property);

        assertEquals(property, createdProperty);
        verify(propertyRepository, times(1)).save(property);
    }

    @Test
    void testGetAllProperties() {
        List<Property> properties = Arrays.asList(property);
        when(propertyRepository.findAll()).thenReturn(properties);

        List<Property> result = propertyService.getAllProperties();

        assertEquals(1, result.size());
        assertEquals(property, result.get(0));
        verify(propertyRepository, times(1)).findAll();
    }

    @Test
    void testGetPropertyById() {
        when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));

        Property foundProperty = propertyService.getPropertyById(1L);

        assertEquals(property, foundProperty);
        verify(propertyRepository, times(1)).findById(1L);
    }

    @Test
    void testGetPropertyByIdNotFound() {
        when(propertyRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            propertyService.getPropertyById(1L);
        });

        assertEquals("Propiedad no encontrada", exception.getMessage());
        verify(propertyRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateProperty() {
        when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));
        when(propertyRepository.save(property)).thenReturn(property);

        Property updatedProperty = propertyService.updateProperty(1L, property);

        assertEquals(property, updatedProperty);
        verify(propertyRepository, times(1)).findById(1L);
        verify(propertyRepository, times(1)).save(property);
    }

    @Test
    void testDeleteProperty() {
        when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));
        doNothing().when(propertyRepository).delete(property);

        propertyService.deleteProperty(1L);

        verify(propertyRepository, times(1)).findById(1L);
        verify(propertyRepository, times(1)).delete(property);
    }
}

