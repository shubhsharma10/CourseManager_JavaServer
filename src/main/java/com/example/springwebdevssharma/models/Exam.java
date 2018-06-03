package com.example.springwebdevssharma.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Exam extends Widget {
  private String title;
  private String description;
  private Integer points;
  @OneToMany(mappedBy="exam")
  @JsonIgnore
  private List<Question> questions;

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
  public List<Question> getQuestions() {
    return questions;
  }
  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }
}
