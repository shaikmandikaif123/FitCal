package com.fitcal.api.controller;

import com.fitcal.api.model.User;
import com.fitcal.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves all users.
     * @return A list of User objects.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Checks if a user with the specified email exists.
     * @param email The email of the user to check.
     * @return ResponseEntity with the response body as the found user (HttpStatus.OK) if it exists, 
     * or an empty response (HttpStatus.NOT_FOUND) if it doesn't exist.
     */
    @GetMapping("/{email}")
    public ResponseEntity<User> checkUserExists(@PathVariable("email") String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Creates a new user profile.
     * @param user The User object to create.
     * @return ResponseEntity with the response body as the created user (HttpStatus.CREATED).
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Updates an existing user profile with the specified ID.
     * @param id   The ID of the user to update.
     * @param user The User object containing the new user data.
     * @return ResponseEntity with the response body as the updated user (HttpStatus.OK) if updated successfully,
     * or an empty response (HttpStatus.NOT_FOUND) if the user is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUserProfile(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes an existing user with the specified ID.
     * @param id The ID of the user to delete.
     * @return ResponseEntity with an empty response (HttpStatus.NO_CONTENT) if deleted successfully,
     * or an empty response (HttpStatus.NOT_FOUND) if the user is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
