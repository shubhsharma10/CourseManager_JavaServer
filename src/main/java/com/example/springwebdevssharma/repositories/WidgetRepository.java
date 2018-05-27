package com.example.springwebdevssharma.repositories;

import com.example.springwebdevssharma.models.Widget;

import javax.transaction.Transactional;

@Transactional
public interface WidgetRepository extends WidgetBaseRepository<Widget> {

}
