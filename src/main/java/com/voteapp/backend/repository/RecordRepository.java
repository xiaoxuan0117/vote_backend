package com.voteapp.backend.repository;

import com.voteapp.backend.modal.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

  @Query(nativeQuery = true, value = "call getRecords")
  List<Record> getRecords();

  @Transactional
  @Modifying
  @Query(nativeQuery = true, value = "call addRecord(:username, :itemId)")
  void addRecord(@Param("username") String username, @Param("itemId") int itemId);

  @Transactional
  @Modifying
  @Query(nativeQuery = true, value = "call removeRecord(:username, :itemId)")
  void removeRecord(@Param("username") String username, @Param("itemId") int itemId);
}
