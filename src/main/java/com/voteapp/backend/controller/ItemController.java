package com.voteapp.backend.controller;

import com.voteapp.backend.modal.Item;
import com.voteapp.backend.modal.UpdateItem;
import com.voteapp.backend.repository.ItemRepository;
import com.voteapp.backend.repository.ItemVotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ItemController {
  @Autowired
  ItemRepository itemRepository;

  @Autowired
  ItemVotesRepository itemVotesRepository;

  @GetMapping("/item")
  public ResponseEntity<List<Item>> getItems() {
    try {
      return new ResponseEntity<>(itemRepository.getItems(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/item")
  public ResponseEntity<HttpStatus> addNewItem(@RequestBody Item item) {
    try {
      itemRepository.addItem(item.getName());
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/item/{id}")
  public ResponseEntity<HttpStatus> removeOldItem(@PathVariable("id") int id) {
    try {
      itemRepository.removeItem(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PatchMapping("/item")
  public ResponseEntity<HttpStatus> updateItem(@RequestBody UpdateItem data) {
    try {
      itemRepository.updateItem(data.getNewName(), data.getItemId());
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
