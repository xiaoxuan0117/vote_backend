package com.voteapp.backend.repository;

import com.voteapp.backend.modal.ItemVotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ItemVotesRepository extends JpaRepository<ItemVotes, Long> {

  @Query(nativeQuery = true, value = "call getItemsVotes")
  List<ItemVotes> getItemsVotes();
}
