package com.example.springwebdevssharma.models;

import javax.persistence.Entity;

@Entity
public class FillInTheBlanksQuestion extends Question{

  private String inputArea="";

  public String getInputArea() {
    return inputArea;
  }

  public void setInputArea(String inputArea) {
    this.inputArea = inputArea;
  }

}
