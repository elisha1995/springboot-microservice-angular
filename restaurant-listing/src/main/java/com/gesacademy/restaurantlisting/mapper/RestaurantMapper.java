package com.gesacademy.restaurantlisting.mapper;

import com.gesacademy.restaurantlisting.dto.RestaurantDto;
import com.gesacademy.restaurantlisting.entity.Restaurant;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    // ─────────────── Entity ⇨ DTO ───────────────
    RestaurantDto toDto(Restaurant restaurant);

    // ─────────────── DTO ⇨ Entity ───────────────
    Restaurant toEntity(RestaurantDto restaurantDto);

    /**
     * Convenient patch method for PUT/PATCH endpoints.
     * Only non-null values from the DTO overwrite the target.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Restaurant target, RestaurantDto source);
}
