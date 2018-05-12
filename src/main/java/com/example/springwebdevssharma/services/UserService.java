package com.example.springwebdevssharma.services;

import com.example.springwebdevssharma.models.User;
import com.example.springwebdevssharma.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

  @DeleteMapping("/api/user/{userId}")
  public void deleteUser(@PathVariable("userId") int id) {
    repository.deleteById(id);
  }
}
