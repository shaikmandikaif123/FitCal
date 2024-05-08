package com.fitcal.api.service;

import com.fitcal.api.model.Food;
import com.fitcal.api.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    /**
     * Get all foods.
     * @return A list of all existing foods in the database.
     */
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    /**
     * Get a food by its ID.
     * @param id The ID of the food to get.
     * @return The found food or null if not found.
     */
    public Food getFoodById(Long id) {
        return foodRepository.findById(id).orElse(null);
    }

    /**
     * Create a new food.
     * @param food The food to create.
     * @return The created food.
     */
    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    /**
     * Update an existing food.
     * @param id The ID of the food to update.
     * @param updatedFood The updated Food object.
     * @return The updated food, if exists; otherwise, returns null.
     */
    public Food updateFood(Long id, Food updatedFood) {
        if (foodRepository.existsById(id)) {
            updatedFood.setId(id);
            return foodRepository.save(updatedFood);
        } else {
            return null;
        }
    }

    /**
     * Delete an existing food.
     * @param id The ID of the food to delete.
     * @return true if the food was deleted successfully; false if the food was not found.
     */
    public boolean deleteFood(Long id) {
        if (foodRepository.existsById(id)) {
            foodRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
