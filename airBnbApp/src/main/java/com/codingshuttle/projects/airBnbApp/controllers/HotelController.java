package com.codingshuttle.projects.airBnbApp.controllers;

import com.codingshuttle.projects.airBnbApp.dtos.HotelDto;
import com.codingshuttle.projects.airBnbApp.servicies.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/hotels")
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDto> createNewHotel(@RequestBody HotelDto hotelDto){
        return new ResponseEntity<>(hotelService.createNewHotel(hotelDto), HttpStatus.CREATED) ;
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable long hotelId){
        return new ResponseEntity<>(hotelService.getHotelById(hotelId), HttpStatus.FOUND) ;
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelDto> updateHotelById(@PathVariable long hotelId,@RequestBody HotelDto hotelDto){
        HotelDto hotelDto1 =hotelService.updateHotelById(hotelId,hotelDto);
        return  ResponseEntity.ok(hotelDto1);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotelById(@PathVariable long hotelId){
        hotelService.deleteHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{hotelId}")
    public ResponseEntity<Void> activateHotelById(@PathVariable long hotelId){
        hotelService.activateHotelyId(hotelId);
        return ResponseEntity.noContent().build();
    }
}
