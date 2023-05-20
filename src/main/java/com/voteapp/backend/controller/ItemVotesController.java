package com.voteapp.backend.controller;

import com.voteapp.backend.modal.ItemVotes;
import com.voteapp.backend.repository.ItemRepository;
import com.voteapp.backend.repository.ItemVotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ItemVotesController {
  @Autowired
  ItemRepository itemRepository;

  @Autowired
  ItemVotesRepository itemVotesRepository;

  @GetMapping("/votes")
  public ResponseEntity<List<ItemVotes>> getItemsVotes() {
    try {
      return new ResponseEntity<>(itemVotesRepository.getItemsVotes(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
