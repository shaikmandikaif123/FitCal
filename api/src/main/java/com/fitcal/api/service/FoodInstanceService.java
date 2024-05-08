package com.fitcal.api.service;

import com.fitcal.api.model.FoodInstance;
import com.fitcal.api.repository.FoodInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodInstanceService {

    private final FoodInstanceRepository foodInstanceRepository;

    @Autowired
    public FoodInstanceService(FoodInstanceRepository foodInstanceRepository) {
        this.foodInstanceRepository = foodInstanceRepository;
    }

    /**
     * Get all food instances.
     * @return A list of all existing food instances in the database.
     */
    public List<FoodInstance> getAllFoodInstances() {
        return foodInstanceRepository.findAll();
    }

    /**
     * Get a food instance by its ID.
     * @param id The ID of the food instance to get.
     * @return An Optional containing the found food instance or empty if not found.
     */
    public Optional<FoodInstance> getFoodInstanceById(Long id) {
        return foodInstanceRepository.findById(id);
    }

    /**
     * Find food instances by day ID.
     * @param dayId The ID of the day.
     * @return A list of food instances belonging to the day with the specified ID.
     */
    public List<FoodInstance> findByIdDay(Long dayId) {
        return foodInstanceRepository.findByDayId(dayId);
    }

    /**
     * Create a new food instance.
     * @param foodInstance The food instance to create.
     * @return The created food instance.
     */
    public FoodInstance createFoodInstance(FoodInstance foodInstance) {
        System.out.println(foodInstance);
        return foodInstanceRepository.save(foodInstance);
    }

    /**
     * Update an existing food instance.
     * @param id The ID of the food instance to update.
     * @param updatedFoodInstance The updated food instance.
     * @return The updated food instance, if exists; otherwise, returns null or throws an exception.
     */
    public FoodInstance updateFoodInstance(Long id, FoodInstance updatedFoodInstance) {
        Optional<FoodInstance> existingFoodInstance = foodInstanceRepository.findById(id);
        if (existingFoodInstance.isPresent()) {
            FoodInstance foodInstance = existingFoodInstance.get();
            foodInstance.setFood(updatedFoodInstance.getFood());
            foodInstance.setMealType(updatedFoodInstance.getMealType());
            foodInstance.setGrams(updatedFoodInstance.getGrams());
            foodInstance.setDay(updatedFoodInstance.getDay());
            return foodInstanceRepository.save(foodInstance);
        }
        return null; // or throw an exception
    }

    /**
     * Delete an existing food instance.
     * @param id The ID of the food instance to delete.
     * @return true if the food instance was deleted successfully; false if the food instance was not found.
     */
    public boolean deleteFoodInstance(Long id) {
        Optional<FoodInstance> existingFoodInstance = foodInstanceRepository.findById(id);
        if (existingFoodInstance.isPresent()) {
            foodInstanceRepository.delete(existingFoodInstance.get());
            return true;
        }
        return false;
    }
}
