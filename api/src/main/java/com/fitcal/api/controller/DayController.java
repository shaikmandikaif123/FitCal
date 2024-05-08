package com.fitcal.api.controller;

import com.fitcal.api.model.Day;
import com.fitcal.api.service.DayService;

import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/days")
public class DayController {

    private final DayService dayService;

    @Autowired
    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    /**
     * Retrieves all days.
     * @return A ResponseEntity object containing the list of days and 
     * the OK status.
     */
    @GetMapping
    public ResponseEntity<List<Day>> getAllDays() {
        List<Day> days = dayService.getAllDays();
        return new ResponseEntity<>(days, HttpStatus.OK);
    }

    /**
     * Retrieves a day by its ID.
     * @param id The ID of the day.
     * @return A ResponseEntity object containing the found day 
     * and the OK status, or the NOT_FOUND status if the day is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Day> getDayById(@PathVariable("id") Long id) {
        Optional<Day> day = dayService.getDayById(id);
        return day.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Searches for days by date and user.
     * @param date The date.
     * @param userId The user ID.
     * @return A list of days matching the specified date and user.
     */    
    @GetMapping("/search")
    public List<Day> searchByDateAndUser(@RequestParam @NotNull LocalDate date,
                                        @RequestParam @NotNull Long userId) {
        return dayService.findByDateAndUserId(date, userId);
    }

    /**
     * Creates a new day.
     * @param day The Day object to create.
     * @return A ResponseEntity object containing the created day 
     * and the CREATED status.
     */
    @PostMapping
    public ResponseEntity<Day> createDay(@RequestBody Day day) {
        Day createdDay = dayService.createDay(day);
        return new ResponseEntity<>(createdDay, HttpStatus.CREATED);
    }

    /**
     * Updates an existing day.
     * @param id The ID of the day to update.
     * @param updatedDay The updated Day object.
     * @return A ResponseEntity object containing the updated day 
     * and the OK status, or the NOT_FOUND status if the day is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Day> updateDay(@PathVariable("id") Long id, @RequestBody Day updatedDay) {
        Day day = dayService.updateDay(id, updatedDay);
        return day != null ? new ResponseEntity<>(day, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Deletes a day by its ID.
     * @param id The ID of the day to delete.
     * @return A ResponseEntity object with the NO_CONTENT status if 
     * the day is deleted, or the NOT_FOUND status if the day is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDay(@PathVariable("id") Long id) {
        boolean deleted = dayService.deleteDay(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
