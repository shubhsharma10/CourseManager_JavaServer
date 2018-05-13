package com.example.springwebdevssharma.services;

import com.example.springwebdevssharma.models.User;
import com.example.springwebdevssharma.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserService {
  @Autowired
  UserRepository repository;

  @PostMapping("/api/user")
  public User createUser(@RequestBody User user) {
    return repository.save(user);
  }

  @GetMapping("/api/user")
  public List<User> findAllUsers() {
    return (List<User>) repository.findAll();
  }

  @PostMapping("/api/login")
  public List<User> login(@RequestBody User user) {
    return (List<User>) repository.findUserByCredentials(user.getUsername(), user.getPassword());
  }

  @DeleteMapping("/api/user/{userId}")
  public void deleteUser(@PathVariable("userId") int id) {
    repository.deleteById(id);
  }

  @PutMapping("/api/user/{userId}")
  public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
    Optional<User> data = repository.findById(userId);
    if(data.isPresent()) {
      User user = data.get();
      user.setFirstName(newUser.getFirstName());
      user.setLastName(newUser.getLastName());
      user.setUsername(newUser.getUsername());
      user.setPassword(newUser.getPassword());
      user.setRole(newUser.getRole());
      repository.save(user);
      return user;
    }
    return null;
  }

  @GetMapping("/api/user/{userId}")
  public User findUserById(@PathVariable("userId") int userId) {
    Optional<User> data = repository.findById(userId);
    if(data.isPresent()) {
      User user = data.get();
      return user;
    }
    return null;
  }
}
