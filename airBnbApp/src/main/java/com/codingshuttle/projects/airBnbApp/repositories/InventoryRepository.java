package com.codingshuttle.projects.airBnbApp.repositories;

import com.codingshuttle.projects.airBnbApp.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}