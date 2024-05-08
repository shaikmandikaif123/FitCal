package com.fitcal.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A day has a UserData
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;


    @Column(nullable = false)
    private LocalDate date;

    // A day can have many FoodInstances
    @JsonIgnore
    @OneToMany(mappedBy = "day", orphanRemoval = true)
    private List<FoodInstance> foodInstances = new ArrayList<>();
}
