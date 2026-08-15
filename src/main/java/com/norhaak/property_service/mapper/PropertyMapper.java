package com.norhaak.property_service.mapper;

import com.norhaak.property_service.dto.PropertyDTO;
import com.norhaak.property_service.model.Property;

public class PropertyMapper {

    public static PropertyDTO toDTO(Property property) {
        return PropertyDTO.builder()
                .id(property.getId())
                .title(property.getTitle())
                .location(property.getLocation())
                .price(property.getPrice())
                .description(property.getDescription())
                .imageUrl(property.getImageUrl())
                .build();
    }

    public static Property toEntity(PropertyDTO dto) {
        return Property.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .location(dto.getLocation())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .imageUrl(dto.getImageUrl())
                .build();

    }
}
