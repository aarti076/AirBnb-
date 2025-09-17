package com.codingshuttle.projects.airBnbApp.repositories;

import com.codingshuttle.projects.airBnbApp.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}