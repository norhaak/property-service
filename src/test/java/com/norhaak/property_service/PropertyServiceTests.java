package com.norhaak.property_service;

import com.norhaak.property_service.exception.ResourceNotFoundException;
import com.norhaak.property_service.model.Property;
import com.norhaak.property_service.repository.PropertyRepository;
import com.norhaak.property_service.service.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTests {

    @Mock
    private PropertyRepository repository;

    @InjectMocks
    private PropertyService service;

    @Test
    void testCreateProperty() {
        Property property = Property.builder()
                .title("Crazy Studio")
                .location("Casablanca")
                .price(50000.0)
                .description("Nice place near downtown")
                .build();

        when(repository.save(any(Property.class))).thenReturn(property);

        Property created = service.create(property);

        assertThat(created.getTitle()).isEqualTo("Crazy Studio");
        verify(repository, times(1)).save(property);
    }

    @Test
    void testFindByIdSuccess() {
        Property property = Property.builder().id(1L).title("Studio").build();
        when(repository.findById(1L)).thenReturn(Optional.of(property));

        Property found = service.findById(1L);

        assertThat(found.getId()).isEqualTo(1L);
        assertThat(found.getTitle()).isEqualTo("Studio");
    }

    @Test
    void findByIdNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(99L));
    }

    @Test
    void testUpdateProperty() {
        Property existing = Property.builder().id(1L).title("Old").build();
        Property updated = Property.builder().id(1L).title("New").build();

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Property.class))).thenReturn(updated);

        Property result = service.update(1L, updated);

        assertThat(result.getTitle()).isEqualTo("New");
    }

    @Test
    void testDeleteProperty() {
        Property property = Property.builder().id(1L).title("Studio").build();

        when(repository.findById(1L)).thenReturn(Optional.of(property));

        service.delete(1L);

        verify(repository, times(1)).delete(property);
    }


}
