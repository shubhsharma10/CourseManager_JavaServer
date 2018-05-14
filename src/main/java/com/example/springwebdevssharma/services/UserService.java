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

import javax.servlet.http.HttpSession;

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
  public User login(@RequestBody User user) {
    List<User> users = (List<User>) repository.findUserByCredentials(user.getUsername(), user.getPassword());
    if(users.size() == 1)
    {
      return users.get(0);
    }
    else
    {
      return null;
    }
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
      user.setPhone(newUser.getPhone());
      user.setEmail(newUser.getEmail());
      user.setDateOfBirth(newUser.getDateOfBirth());
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

  @PostMapping("/api/register")
  public User register(@RequestBody User user, HttpSession session) {
    List<User> existingUsers = findUserByUsername(user.getUsername());
    if(existingUsers.size() == 0)
    {
      createUser(user);
      session.setAttribute("user",user);
      return user;
    }
    return null;

  }

  private List<User> findUserByUsername(String username) {
    return (List<User>)repository.findUserByUsername(username);
  }
}
