package com.example.springwebdevssharma.models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
public class MultipleChoiceQuestion extends Question {
  @ElementCollection
  private List<String> options;
  private String correctOption;
  public List<String> getOptions() {
    return options;
  }
  public void setOptions(List<String> options) {
    this.options = options;
  }
  public String getCorrectOption() {
    return correctOption;
  }
  public void setCorrectOption(String correctOption) {
    this.correctOption = correctOption;
  }
}