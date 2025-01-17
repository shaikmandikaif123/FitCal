package com.fitcal.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public User(Long id, String email, String googleId, String name, String photoUrl) {
        this.id = id;
        this.email = email;
        this.googleId = googleId;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    // Login
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 40, nullable = false)
    private String email;

    @Lob
    @Column(nullable = false, length = 1000)
    private String googleId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String photoUrl;

    // Information
    @Column(columnDefinition = "double default 50.0")
    private double weight;

    @Column
    private int height;

    @Column
    private int calories;

    @Column
    private char gender;

    @Column
    private LocalDate birth_date;

    @Column
    private String goal;

    @Column
    private String activityLevel;

    @Column
    private double proteinsGoal;

    @Column
    private double carbsGoal;

    @Column
    private double fatsGoal;

    // A User has many days
    @JsonIgnore
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Day> days = new ArrayList<>();

    // A User has many weightDays
    @JsonIgnore
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<WeightDay> weightDays = new ArrayList<>();
}
