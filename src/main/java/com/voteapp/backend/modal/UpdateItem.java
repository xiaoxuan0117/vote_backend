package com.voteapp.backend.modal;

import lombok.*;
import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")

public class UpdateItem {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "newName")
  private String newName;

  @Column(name = "itemId")
  private int itemId;

  public int getId() {
    return id;
  }

  public void setNewName(String newName) {
    this.newName = newName;
  }

  public String getNewName() {
    return this.newName;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public int getItemId() {
    return this.itemId;
  }
}
