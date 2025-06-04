package com.gesacademy.restaurantlisting.service;

import com.gesacademy.restaurantlisting.dto.RestaurantDto;
import com.gesacademy.restaurantlisting.entity.Restaurant;
import com.gesacademy.restaurantlisting.mapper.RestaurantMapper;
import com.gesacademy.restaurantlisting.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    /**
     * Get all restaurants.
     *
     * @return a list of all restaurants in the database, each represented as a {@link RestaurantDto}
     */
    public List<RestaurantDto> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(restaurantMapper::toDto)
                .toList();
    }


    /**
     * Create and persist a new {@link Restaurant}.
     *
     * @param restaurantDto the data for the restaurant to create
     * @return the persisted restaurant as a {@link RestaurantDto}
     */
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantMapper.toEntity(restaurantDto);
        Restaurant saved = restaurantRepository.save(restaurant);
        return restaurantMapper.toDto(saved);
    }
}
