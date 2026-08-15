package com.norhaak.property_service.service;

import com.norhaak.property_service.exception.ResourceNotFoundException;
import com.norhaak.property_service.model.Property;
import com.norhaak.property_service.repository.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PropertyService {

    private final PropertyRepository repository;

    public PropertyService(PropertyRepository repository) {
        this.repository = repository;
    }

    public Property create(Property property) {
        log.info("Creating property: {}", property.getTitle());
        return repository.save(property);
    }

    public List<Property> findAll() {
        return repository.findAll();
    }

    public Property findById(Long id) {
        log.debug("Fetching property with id {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id " + id));
    }

    public Property update(Long id, Property updated) {
        log.info("Updating property with id {}", id);
        Property existing = findById(id);
        existing.setTitle(updated.getTitle());
        existing.setLocation(updated.getLocation());
        existing.setPrice(updated.getPrice());
        existing.setDescription(updated.getDescription());
        existing.setImageUrl(updated.getImageUrl());
        return repository.save(existing);
    }

    public void delete(Long id) {
        log.warn("Deleting property with id {}", id);
        Property property = repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Property not found with id " + id));
        repository.delete(property);
    }
}
