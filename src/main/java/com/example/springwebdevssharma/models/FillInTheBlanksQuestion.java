package com.example.springwebdevssharma.models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
public class FillInTheBlanksQuestion extends Question{

  @ElementCollection
  private List<String> paramValues;

  public List<String> getParamValues() {
    return paramValues;
  }

  public void setParamValues(List<String> paramValues) {
    this.paramValues = paramValues;
  }

}
