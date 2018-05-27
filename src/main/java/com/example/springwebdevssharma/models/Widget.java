package com.example.springwebdevssharma.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="WidgetType")
@Table(name="Widget")
public abstract class Widget {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id = 1;
  private String text;
  private String name;
  private int widgetOrder;
  private String className;
  private String style;
  private String width;
  private String height;

  @ManyToOne
  @JsonIgnore
  private Topic topic;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getWidgetOrder() {
    return widgetOrder;
  }

  public void setWidgetOrder(int order) {
    this.widgetOrder = widgetOrder;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getStyle() {
    return style;
  }

  public void setStyle(String style) {
    this.style = style;
  }

  public String getWidth() {
    return width;
  }

  public void setWidth(String width) {
    this.width = width;
  }

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }
}
