package com.example.springwebdevssharma.repositories;

import com.example.springwebdevssharma.models.Widget;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface WidgetRepository extends CrudRepository<Widget,Integer> {

}
