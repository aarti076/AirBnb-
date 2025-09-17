package com.codingshuttle.projects.airBnbApp.servicies;

import com.codingshuttle.projects.airBnbApp.entities.Inventory;
import com.codingshuttle.projects.airBnbApp.entities.Room;
import com.codingshuttle.projects.airBnbApp.exceptions.ResourceNotFoundException;
import com.codingshuttle.projects.airBnbApp.repositories.InventoryRepository;
import com.codingshuttle.projects.airBnbApp.repositories.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImple implements InventoryService{

    private final InventoryRepository inventoryRepository;

    private final RoomRepository roomRepository;
    @Transactional
    @Override
    public void initilizeRoomForAYear(Room room) {
        log.info("Creating inventory for future 1 one year for room with id : "+room.getId());
      LocalDate today = LocalDate.now();
      LocalDate endDate =today.plusYears(1);
 for( ;!today.isAfter(endDate);today=today.plusDays(1)){
            Inventory inventory = Inventory.builder()
                    .room(room)
                    .hotel(room.getHotel())
                    .date(today)
                    .bookedCount(0)
                    .totalCount(room.getTotalCount())
                    .city(room.getHotel().getCity())
                    .closed(false)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .build();
            inventoryRepository.save(inventory);
        }
        log.info("Created inventory for future 1 one year for room with id : "+room.getId());

    }
}
