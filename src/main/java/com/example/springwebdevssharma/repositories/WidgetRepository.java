package com.example.springwebdevssharma.repositories;

import com.example.springwebdevssharma.models.Widget;

import org.springframework.data.repository.CrudRepository;

public interface WidgetRepository extends CrudRepository<Widget,Integer> {

}
