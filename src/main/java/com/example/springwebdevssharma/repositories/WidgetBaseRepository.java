package com.example.springwebdevssharma.repositories;

import com.example.springwebdevssharma.models.Widget;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface WidgetBaseRepository<T extends Widget>
extends CrudRepository<T,Integer>
{

}
