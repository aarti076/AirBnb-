package com.codingshuttle.projects.airBnbApp.servicies;

import com.codingshuttle.projects.airBnbApp.dtos.RoomDto;
import com.codingshuttle.projects.airBnbApp.entities.Hotel;
import com.codingshuttle.projects.airBnbApp.entities.Room;
import com.codingshuttle.projects.airBnbApp.exceptions.ResourceNotFoundException;
import com.codingshuttle.projects.airBnbApp.repositories.HotelRepository;
import com.codingshuttle.projects.airBnbApp.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImple implements RoomService {

    private final RoomRepository roomRepository;

    private final ModelMapper mapper;

    private final HotelRepository hotelRepository;

    @Override
    public RoomDto createNewRoom(RoomDto roomDto, Long hotelId) {
        log.info("Creating room in hotel id :"+hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(
                        ()->
                                new ResourceNotFoundException("Hotel of id "+hotelId+" not found so cannot create room of it."));
         Room room=mapper.map(roomDto,Room.class);
         room.setHotel(hotel);
         room =roomRepository.save(room);
         //TODO : if hotel is already active create this rooms inventory
        log.info("Created room in hotel id :"+hotelId+"of room id as "+room.getId());
        return mapper.map(room,RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Getting all rooms of hotel id :"+hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(
                        ()->
                                new ResourceNotFoundException("Hotel of id "+hotelId+" not found so cannot create room of it."));
        List<RoomDto> roomDtos = hotel.getRooms()
                .stream()
                .map( room -> mapper.map(room,RoomDto.class))
                .toList();
        log.info("Got all rooms of hotel id :"+hotelId);
        return roomDtos;
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting room by room id:"+roomId);
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room of id "+roomId+" not found ."));
        log.info("Got room by room id:"+roomId);
        return mapper.map(room,RoomDto.class);
    }

    @Override
    public void deleteRoomById(Long roomId) {
        log.info("Deleting room by room id:"+roomId);
        boolean exists = roomRepository.existsById(roomId);
        if(!exists) throw new ResourceNotFoundException("Room of id "+roomId+" does not exists so we cannot delete it .");
        //TODO:first delete all future inventory of that room
        roomRepository.deleteById(roomId);
        log.info("Deleted room by room id:"+roomId);
    }
}
