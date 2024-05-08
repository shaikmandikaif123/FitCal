package com.fitcal.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A FoodInstance can have a food
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;
        
    @Column(nullable = false)
    private String mealType;// BREAKFAST, LUNCH, DINNER, SNACKS

    @Column
    private int grams;

    // A FoodInstance belongs to a day
    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;

}
