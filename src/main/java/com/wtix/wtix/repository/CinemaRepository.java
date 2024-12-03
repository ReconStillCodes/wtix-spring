package com.wtix.wtix.repository;

import com.wtix.wtix.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {

    List<Cinema> findByNameContainingIgnoreCase(String name);

}
