package com.voteapp.backend.controller;

import com.voteapp.backend.modal.User;
import com.voteapp.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin
@RestController
public class UserController {
  @Autowired
  UserRepository userRepository;

  @GetMapping("/user/{name}")
  public ResponseEntity<List<Integer>> getUserDetail(@PathVariable("name") String name) {
    Pattern pattern = Pattern.compile("^[\\w\u4e00-\u9fa5]*$");
    Matcher matcher = pattern.matcher(name);
    if (!matcher.matches()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    try {
      return new ResponseEntity<>(userRepository.getUserDetail(name), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/login")
  public ResponseEntity<HttpStatus> login(@RequestBody User user) {
    Pattern pattern = Pattern.compile("^[\\w\u4e00-\u9fa5]*$");
    Matcher matcherName = pattern.matcher(user.getName());
    Matcher matcherPassword = pattern.matcher(user.getName());
    if (!matcherName.matches() || !matcherPassword.matches()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
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
