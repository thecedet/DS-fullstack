package fr.polytech.dsbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.polytech.dsbackend.entity.RestaurantEntity;
import fr.polytech.dsbackend.exception.ResourceNotFoundException;
import fr.polytech.dsbackend.repository.RestaurantRepository;


@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {
    
    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    public void shouldReturnArestaurantEntity() {
        RestaurantEntity restaurant = RestaurantEntity.builder().id(3).build();
        when(restaurantRepository.findById(any())).thenReturn(Optional.of(restaurant));

        RestaurantEntity result = this.restaurantService.getRestaurant(3);

        assertEquals(restaurant, result);
    }

    @Test
    public void shouldReturnAErrorIfIdDoesExists() {
        when(restaurantRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            this.restaurantService.getRestaurant(30);
        });
    }

}
