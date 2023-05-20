package com.voteapp.backend.repository;

import com.voteapp.backend.modal.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

  @Query(nativeQuery = true, value = "call getItems")
  List<Item> getItems();

  @Transactional
  @Modifying
  @Query(nativeQuery = true, value = "call addItem(:name)")
  void addItem(@Param("name") String name);

  @Transactional
  @Modifying
  @Query(nativeQuery = true, value = "call removeItem(:id)")
  void removeItem(@Param("id") int id);
}
