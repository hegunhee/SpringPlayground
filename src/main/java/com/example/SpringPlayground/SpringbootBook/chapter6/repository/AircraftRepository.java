package com.example.SpringPlayground.SpringbootBook.chapter6.repository;

import com.example.SpringPlayground.SpringbootBook.chapter6.planefinder.Aircraft;
import org.springframework.data.repository.CrudRepository;

public interface AircraftRepository extends CrudRepository<Aircraft,Long> {

}
