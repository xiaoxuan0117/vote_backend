package com.voteapp.backend.repository;

import com.voteapp.backend.modal.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query(nativeQuery = true, value = "call getUserDetail(:username)")
  List<Integer> getUserDetail(@Param("username") String username);

  @Transactional
  @Query(nativeQuery = true, value = "call login(:username, :userPassword)")
  User login(@Param("username") String username, @Param("userPassword") String userPassword);

}
