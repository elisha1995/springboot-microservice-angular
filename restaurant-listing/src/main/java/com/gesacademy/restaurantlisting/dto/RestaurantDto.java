package com.gesacademy.restaurantlisting.dto;

public record RestaurantDto(
        Long   id,
        String name,
        String address,
        String city,
        String restaurantDescription
) { }
