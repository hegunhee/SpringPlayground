package com.example.SpringPlayground.SpringbootBook.chapter7.repository;

import com.example.SpringPlayground.SpringbootBook.chapter7.domain.Aircraft;
import org.springframework.data.repository.CrudRepository;

public interface AircraftRepository extends CrudRepository<Aircraft,Long> {

}
