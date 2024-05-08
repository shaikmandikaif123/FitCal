package com.fitcal.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitcal.api.model.User;
import com.fitcal.api.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    /**
     * Get all users.
     * @return A list of all existing users in the database.
    */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get a user by their token ID.
     * @param idToken The token ID of the user to get.
     * @return The found user or null if not found.
     */
    public Optional<User> getUserByIdToken(String idToken) {
        return userRepository.findByGoogleId(idToken);
    }

    /**
     * Get a user by their email address.
     * @param email The email address of the user to get.
     * @return The found user or null if not found.
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Create a new user.
     * @param user The User object to create.
     * @return The created user.
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Update an existing user by their ID.
     * @param id The ID of the user to update.
     * @param updatedUser The updated User object.
     * @return The updated user, if exists; otherwise, returns null.
     */
    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        }
        return null; // or throw an exception
    }

    /**
     * Update an existing user's profile by their ID.
     * @param id The ID of the user to update.
     * @param updatedUser The updated User object with new profile data.
     * @return The updated user, if exists; otherwise, returns null.
     */
    public User updateUserProfile(Long id, User updatedUser) {
        if (userRepository.existsById(id)) {
            updatedUser.setId(id);
            return userRepository.save(updatedUser);
        } else {
            return null;
        }
    }

    /**
     * Delete an existing user by their ID.
     * @param id The ID of the user to delete.
     * @return true if the user was deleted successfully; 
     * false if the user was not found.
     */
    public boolean deleteUser(Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userRepository.delete(existingUser.get());
            return true;
        }
        return false;
    }

    /**
     * Find a user by their email address.
     * @param email The email address of the user to find.
     * @return The found user or null if not found.
     */
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

}
