package com.voteapp.backend.modal;

import jakarta.persistence.*;

@Entity
@Table

public class Record {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "item_id")
  private int itemId;

  public Record(String name, int itemId) {
    this.name = name;
    this.itemId = itemId;
  }

  public int getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public int getItemId() {
    return this.itemId;
  }
}
