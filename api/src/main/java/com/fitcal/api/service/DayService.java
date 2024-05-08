package com.fitcal.api.service;

import com.fitcal.api.model.Day;
import com.fitcal.api.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DayService {

    private final DayRepository dayRepository;

    @Autowired
    public DayService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    /**
     * Get all days.
     * @return A list of all days existing in the database.
     */
    public List<Day> getAllDays() {
        return dayRepository.findAll();
    }

    /**
     * Get a day by its ID.
     * @param id The ID of the day to get.
     * @return An Optional containing the found day or empty if not found.
     */
    public Optional<Day> getDayById(Long id) {
        return dayRepository.findById(id);
    }
    
    /**
     * Create a new day.
     * @param day The Day object to create.
     * @return The created day.
     */
    public Day createDay(Day day) {
        return dayRepository.save(day);
    }

    /**
     * Update an existing day.
     * @param id The ID of the day to update.
     * @param updatedDay The updated Day object.
     * @return The updated day, if exists; otherwise, 
     * returns null or throws an exception.
     */
    public Day updateDay(Long id, Day updatedDay) {
        Optional<Day> existingDay = dayRepository.findById(id);
        if (existingDay.isPresent()) {
            Day day = existingDay.get();
            day.setDate(updatedDay.getDate());
            day.setFoodInstances(updatedDay.getFoodInstances());
            return dayRepository.save(day);
        }
        return null; // or throw an exception
    }

    /**
     * Delete an existing day.
     * @param id The ID of the day to delete.
     * @return true if the day was deleted successfully; false if the day was not found.
     */
    public boolean deleteDay(Long id) {
        Optional<Day> existingDay = dayRepository.findById(id);
        if (existingDay.isPresent()) {
            dayRepository.delete(existingDay.get());
            return true;
        }
        return false;
    }

    /**
     * Find days by date and user ID.
     * @param date The date of the day to find.
     * @param userId The ID of the user.
     * @return A list of days matching the date and user ID.
     */    
    public List<Day> findByDateAndUserId(LocalDate date, Long userId) {
        return dayRepository.findByDateAndUserId(date, userId);
    }
}
