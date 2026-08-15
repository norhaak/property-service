package com.norhaak.property_service.controller;


import com.norhaak.property_service.dto.PropertyDTO;
import com.norhaak.property_service.mapper.PropertyMapper;
import com.norhaak.property_service.model.Property;
import com.norhaak.property_service.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService service;

    public PropertyController(PropertyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PropertyDTO> create(@Valid @RequestBody PropertyDTO dto) {
        Property created = service.create(PropertyMapper.toEntity(dto));
        return ResponseEntity.ok(PropertyMapper.toDTO(created));
    }

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAll() {

        return ResponseEntity.ok(
                service.findAll().stream()
                        .map(PropertyMapper::toDTO)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(PropertyMapper.toDTO(service.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody PropertyDTO dto
    ) {
        Property updated = service.update(id, PropertyMapper.toEntity(dto));
        return ResponseEntity.ok(PropertyMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
