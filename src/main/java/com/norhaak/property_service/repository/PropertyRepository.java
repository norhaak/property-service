package com.norhaak.property_service.repository;

import com.norhaak.property_service.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
