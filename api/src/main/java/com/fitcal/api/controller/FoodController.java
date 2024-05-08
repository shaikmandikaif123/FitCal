package com.fitcal.api.controller;

import com.fitcal.api.model.Food;
import com.fitcal.api.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/fitcal/food")
public class FoodController {
    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    /**
     * Retrieves all foods.
     * @return A list of Food objects representing all foods.
     */    
    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    /**
     * Retrieves a food by its ID.
     * @param id The ID of the food.
     * @return A ResponseEntity object containing the found food 
     * and the OK status, or the NOT_FOUND status if the food is not found.
    */    
    @GetMapping("/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
        Food food = foodService.getFoodById(id);
        if (food != null) {
            return ResponseEntity.ok(food);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Creates a new food.
     * @param food The Food object to create.
     * @return A ResponseEntity object containing the created food 
     * and the CREATED status.
     */    
    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody Food food) {
        Food createdFood = foodService.createFood(food);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFood);
    }

    /**
     * Updates an existing food.
     * @param id The ID of the food to update.
     * @param food The updated Food object.
     * @return A ResponseEntity object containing the updated food 
     * and the OK status, or the NOT_FOUND status if the food is not found.
     */    
    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable Long id, @RequestBody Food food) {
        Food updatedFood = foodService.updateFood(id, food);
        if (updatedFood != null) {
            return ResponseEntity.ok(updatedFood);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a food by its ID.
     * @param id The ID of the food to delete.
     * @return A ResponseEntity object with the NO_CONTENT status if 
     * the food is deleted, or the NOT_FOUND status if the food is not found.
    */    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        boolean deleted = foodService.deleteFood(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
