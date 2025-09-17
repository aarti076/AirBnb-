package com.codingshuttle.projects.airBnbApp.repositories;

import com.codingshuttle.projects.airBnbApp.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}