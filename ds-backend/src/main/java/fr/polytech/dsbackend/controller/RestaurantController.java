package fr.polytech.dsbackend.controller;

import org.springframework.web.bind.annotation.RestController;

import fr.polytech.dsbackend.dto.request.RestaurantCreateDto;
import fr.polytech.dsbackend.dto.request.RestaurantUpdateDto;
import fr.polytech.dsbackend.dto.response.MessageDto;
import fr.polytech.dsbackend.dto.response.RestaurantDto;
import fr.polytech.dsbackend.dto.response.RestaurantSummaryDto;
import fr.polytech.dsbackend.entity.TagEntity;
import fr.polytech.dsbackend.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequiredArgsConstructor
public class RestaurantController {
    
    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public @ResponseBody List<RestaurantSummaryDto> getRestaurants() {
        return this.restaurantService.getRestaurants().stream().map(
            restaurant -> RestaurantSummaryDto.convertEntitytoDto(restaurant)
        ).toList();
    }

    @GetMapping("/restaurants/{id}")
    public @ResponseBody RestaurantDto getRestaurant(@PathVariable Integer id) {
        return RestaurantDto.convertEntitytoDto(this.restaurantService.getRestaurant(id));
    }

    @PostMapping("/restaurants")
    public @ResponseBody RestaurantDto addRestaurant(@Valid @RequestBody RestaurantCreateDto restaurant) {
        return RestaurantDto.convertEntitytoDto(this.restaurantService.addRestaurant(restaurant));
    }

    @PutMapping("/restaurants/{id}")
    public @ResponseBody RestaurantDto editRestaurant(@PathVariable Integer id, @Valid @RequestBody RestaurantUpdateDto restaurant) {
        return RestaurantDto.convertEntitytoDto(this.restaurantService.editRestaurant(id, restaurant));
    }

    @DeleteMapping("/restaurants/{id}")
    public @ResponseBody MessageDto deleteRestaurant(@PathVariable Integer id) {
        return MessageDto.builder()
            .code("DELETE_OK")
            .message(this.restaurantService.deleteRestaurant(id))
            .build();
    }

    @PutMapping("/restaurants/{id}/tags")
    public @ResponseBody RestaurantDto addTag(@PathVariable Integer id, @RequestParam(value = "tag") ArrayList<TagEntity> tags) {
        return RestaurantDto.convertEntitytoDto(this.restaurantService.addTags(id, tags));
    }

    @PutMapping("/restaurants/{id}/image")
    public @ResponseBody MessageDto putImage(@PathVariable Integer id) {
        return MessageDto.builder()
            .code("GET_IMAGE_URL")
            .message(this.restaurantService.putImage(id))
            .build();
    }

    @GetMapping("/restaurants/{id}/image")
    public @ResponseBody MessageDto getImage(@PathVariable Integer id) {
        return MessageDto.builder()
            .code("GET_IMAGE_URL")
            .message(this.restaurantService.getImage(id))
            .build();
    }
    
}
