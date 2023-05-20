package com.voteapp.backend.controller;

import com.voteapp.backend.modal.Record;
import com.voteapp.backend.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecordController {
  @Autowired
  RecordRepository RecordRepository;

  @PostMapping("/record")
  public ResponseEntity<HttpStatus> addNewRecord(@RequestBody Record Record) {
    try {
      RecordRepository.addRecord(Record.getName(), Record.getItemId());
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/record/{username}/{itemId}")
  public ResponseEntity<HttpStatus> removeOldRecord(@PathVariable("username") String username,
      @PathVariable("itemId") int itemId) {
    try {
      RecordRepository.removeRecord(username, itemId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
