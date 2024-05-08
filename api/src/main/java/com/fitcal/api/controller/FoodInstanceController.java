package com.fitcal.api.controller;

import com.fitcal.api.model.FoodInstance;
import com.fitcal.api.service.FoodInstanceService;

import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/food-instances")
public class FoodInstanceController {

    private final FoodInstanceService foodInstanceService;

    @Autowired
    public FoodInstanceController(FoodInstanceService foodInstanceService) {
        this.foodInstanceService = foodInstanceService;
    }

    /**
     * Retrieves all food instances.
     * @return A list of FoodInstance objects representing all food instances.
     */
    @GetMapping
    public ResponseEntity<List<FoodInstance>> getAllFoodInstances() {
        List<FoodInstance> foodInstances = foodInstanceService.getAllFoodInstances();
        return new ResponseEntity<>(foodInstances, HttpStatus.OK);
    }

    /**
     * Retrieves a food instance by its ID.
     * @param id The ID of the food instance.
     * @return A ResponseEntity object containing the found food instance 
     * and the OK status, or the NOT_FOUND status if the food instance is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FoodInstance> getFoodInstanceById(@PathVariable("id") Long id) {
        Optional<FoodInstance> foodInstance = foodInstanceService.getFoodInstanceById(id);
        return foodInstance.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Searches for food instances by the day ID.
     * @param dayId The ID of the day.
     * @return A list of food instances related to the specified day.
     */
    @GetMapping("/search/{dayId}")
    public List<FoodInstance> searchByIdDay(@PathVariable("dayId") @NotNull Long dayId) {
        return foodInstanceService.findByIdDay(dayId);
    }

    /**
     * Creates a new food instance.
     * @param foodInstance The FoodInstance object to create.
     * @return A ResponseEntity object containing the created food instance 
     * and the CREATED status.
     */
    @PostMapping
    public ResponseEntity<FoodInstance> createFoodInstance(@RequestBody FoodInstance foodInstance) {
        FoodInstance createdFoodInstance = foodInstanceService.createFoodInstance(foodInstance);
        return new ResponseEntity<>(createdFoodInstance, HttpStatus.CREATED);
    }

    /**
     * Updates an existing food instance.
     * @param id The ID of the food instance to update.
     * @param updatedFoodInstance The updated FoodInstance object.
     * @return A ResponseEntity object containing the updated food instance 
     * and the OK status, or the NOT_FOUND status if the food instance is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FoodInstance> updateFoodInstance(@PathVariable("id") Long id,
                                                        @RequestBody FoodInstance updatedFoodInstance) {
        FoodInstance foodInstance = foodInstanceService.updateFoodInstance(id, updatedFoodInstance);
        return foodInstance != null ? new ResponseEntity<>(foodInstance, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Deletes a food instance by its ID.
     * @param id The ID of the food instance to delete.
     * @return A ResponseEntity object with the NO_CONTENT status if the 
     * food instance is deleted, or the NOT_FOUND status if the food instance 
     * is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodInstance(@PathVariable("id") Long id) {
        boolean deleted = foodInstanceService.deleteFoodInstance(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
