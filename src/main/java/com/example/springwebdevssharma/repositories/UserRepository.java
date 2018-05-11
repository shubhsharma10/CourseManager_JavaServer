package com.example.springwebdevssharma.repositories;

import com.example.springwebdevssharma.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository
        extends CrudRepository<User, Integer> {

}
