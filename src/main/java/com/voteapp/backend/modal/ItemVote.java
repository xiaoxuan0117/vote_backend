package com.voteapp.backend.modal;

import jakarta.persistence.*;

@Entity
@Table

public class ItemVote {

  @Id
  private int id;
  private int itemId;
  private int voteCount;

  public ItemVote(int itemId, int voteCount) {
    this.itemId = itemId;
    this.voteCount = voteCount;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public int getItemId() {
    return this.itemId;
  }

  public void setVoteCount(int voteCount) {
    this.voteCount = voteCount;
  }

  public int getVoteCount() {
    return this.voteCount;
  }
}
