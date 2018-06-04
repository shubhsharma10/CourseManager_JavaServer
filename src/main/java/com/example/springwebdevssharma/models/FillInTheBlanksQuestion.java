package com.example.springwebdevssharma.models;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class FillInTheBlanksQuestion extends Question{

  private List<String> parameters;
  private List<Integer> values;

  public List<String> getParameters() {
    return parameters;
  }

  public void setParameters(List<String> parameters) {
    this.parameters = parameters;
  }

  public List<Integer> getValues() {
    return values;
  }

  public void setValues(List<Integer> values) {
    this.values = values;
  }
}
