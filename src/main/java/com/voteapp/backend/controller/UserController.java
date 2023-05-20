package com.voteapp.backend.controller;

import com.voteapp.backend.modal.User;
import com.voteapp.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {
  @Autowired
  UserRepository userRepository;

  @GetMapping("/user/{name}")
  public ResponseEntity<List<Integer>> getUserDetail(@PathVariable("name") String name) {
    try {
      return new ResponseEntity<>(userRepository.getUserDetail(name), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/login")
  public ResponseEntity<HttpStatus> login(@RequestBody User user) {
    try {
      Optional<User> userObj = Optional.ofNullable(userRepository.login(user.getName(), user.getPassword()));

      if (userObj.isPresent()) {
        return new ResponseEntity<>(HttpStatus.OK);
      }

      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
