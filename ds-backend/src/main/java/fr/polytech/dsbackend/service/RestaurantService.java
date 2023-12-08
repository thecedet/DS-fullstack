package fr.polytech.dsbackend.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.polytech.dsbackend.dto.request.RestaurantCreateDto;
import fr.polytech.dsbackend.dto.request.RestaurantUpdateDto;
import fr.polytech.dsbackend.entity.RestaurantEntity;
import fr.polytech.dsbackend.entity.TagEntity;
import fr.polytech.dsbackend.exception.ResourceNotFoundException;
import fr.polytech.dsbackend.repository.RestaurantRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<RestaurantEntity> getRestaurants() {
        return this.restaurantRepository.findAll();
    }

    public RestaurantEntity getRestaurant(Integer id) {
        return this.restaurantRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("L'id est introuvable")
        );
    }

    public RestaurantEntity addRestaurant(RestaurantCreateDto restaurant) {
        final RestaurantEntity entity = RestaurantEntity.builder()
            .nom(restaurant.getNom())
            .addresse(restaurant.getAddresse())
            .evaluations(Collections.emptyList())
            .tags(Collections.emptyList())
            .build();

        this.restaurantRepository.save(entity);
        
        return entity;
    }

    public RestaurantEntity editRestaurant(Integer id, RestaurantUpdateDto restaurant) {
        final RestaurantEntity entity = this.getRestaurant(id);

        if(restaurant.getNom() != null) entity.setNom(restaurant.getNom());
        if(restaurant.getAddresse() != null) entity.setAddresse(restaurant.getAddresse());

         this.restaurantRepository.save(entity);
         return entity;
    }

    public String deleteRestaurant(Integer id) {
        this.getRestaurant(id);
        this.restaurantRepository.deleteById(id);
        return "Le restaurant vient d'être supprimé";
    }

    public RestaurantEntity addTags(Integer id, List<TagEntity> tags) {
        RestaurantEntity entity = this.getRestaurant(id);
        entity.setTags(tags);
        this.restaurantRepository.save(entity);
        return entity;
    }

}