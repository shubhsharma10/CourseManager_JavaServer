package com.example.springwebdevssharma.models;

import javax.persistence.Entity;

@Entity
public class Assignment extends Widget {
  private String title;
  private String description;
  private Integer points;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getPoints() {
    return points;
  }

  public void setPoints(Integer points) {
    this.points = points;
  }
}
