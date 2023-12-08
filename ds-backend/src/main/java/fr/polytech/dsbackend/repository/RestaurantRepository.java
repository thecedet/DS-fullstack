package fr.polytech.dsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.dsbackend.entity.RestaurantEntity;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer>{
    
}
