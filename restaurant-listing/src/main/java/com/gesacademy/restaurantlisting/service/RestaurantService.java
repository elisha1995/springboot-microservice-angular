package com.gesacademy.restaurantlisting.service;

import com.gesacademy.restaurantlisting.dto.RestaurantDto;
import com.gesacademy.restaurantlisting.entity.Restaurant;
import com.gesacademy.restaurantlisting.mapper.RestaurantMapper;
import com.gesacademy.restaurantlisting.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

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


    /**
     * Find a restaurant by its id.
     *
     * @param id the restaurant's identifier
     * @return the corresponding {@link RestaurantDto}
     * @throws EntityNotFoundException if no restaurant with the given id exists
     */
    public RestaurantDto getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + id));
        return restaurantMapper.toDto(restaurant);
    }


}
