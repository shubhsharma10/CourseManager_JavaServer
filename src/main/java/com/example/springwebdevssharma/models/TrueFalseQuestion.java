package com.example.springwebdevssharma.models;

import javax.persistence.Entity;

@Entity
public class TrueFalseQuestion extends Question {
  private boolean isTrue;
  public boolean getTrue() {
    return isTrue;
  }
  public void setTrue(boolean isTrue) {
    this.isTrue = isTrue;
  }
}