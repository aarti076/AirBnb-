package com.codingshuttle.projects.airBnbApp.servicies;

import com.codingshuttle.projects.airBnbApp.dtos.RoomDto;

import java.util.List;

public interface RoomService {
    RoomDto createNewRoom(RoomDto roomDto,Long hotelId);
    List<RoomDto> getAllRoomsInHotel(Long hotelId);
    RoomDto getRoomById(Long roomId);
    void deleteRoomById(Long roomId);
}
