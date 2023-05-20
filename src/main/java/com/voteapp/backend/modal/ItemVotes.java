package com.voteapp.backend.modal;

import lombok.*;
import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table

public class ItemVotes {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "voteCount")
  private int voteCount;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setVoteCount(int voteCount) {
    this.voteCount = voteCount;
  }

  public int getVoteCount() {
    return this.voteCount;
  }
}
