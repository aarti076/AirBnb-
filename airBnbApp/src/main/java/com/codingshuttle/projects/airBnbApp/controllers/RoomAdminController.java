package com.codingshuttle.projects.airBnbApp.controllers;

import com.codingshuttle.projects.airBnbApp.dtos.RoomDto;
import com.codingshuttle.projects.airBnbApp.servicies.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomAdminController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDto> createNewRoom(@RequestBody RoomDto roomDto, @PathVariable long hotelId){
        return new ResponseEntity<>(roomService.createNewRoom(roomDto,hotelId),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRoomsInHotel(@PathVariable long hotelId){
        return new ResponseEntity<>(roomService.getAllRoomsInHotel(hotelId),HttpStatus.FOUND);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable long roomId){
        return new ResponseEntity<>(roomService.getRoomById(roomId),HttpStatus.FOUND);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable long roomId){
        roomService.deleteRoomById(roomId);
        return ResponseEntity.noContent().build();
    }
}
