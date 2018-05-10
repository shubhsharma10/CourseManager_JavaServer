package com.example.springwebdevssharma.repositories;
import com.example.springwebdevssharma.models.Hello;
import org.springframework.data.repository.CrudRepository;

public interface HelloRepository
        extends CrudRepository<Hello,Integer> {

}

