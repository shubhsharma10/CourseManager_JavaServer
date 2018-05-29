package com.example.springwebdevssharma.repositories;

import com.example.springwebdevssharma.models.Widget;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface WidgetRepository extends CrudRepository<Widget,Integer> {

  @Modifying
  @Query("DELETE FROM Widget w WHERE w.id = :widgetId")
  void customDelete(@Param("widgetId") Integer widgetId);
}
