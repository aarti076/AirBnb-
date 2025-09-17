package com.codingshuttle.projects.airBnbApp.servicies;


import com.codingshuttle.projects.airBnbApp.dtos.HotelDto;
import com.codingshuttle.projects.airBnbApp.entities.Hotel;
import com.codingshuttle.projects.airBnbApp.entities.Room;
import com.codingshuttle.projects.airBnbApp.exceptions.ResourceNotFoundException;
import com.codingshuttle.projects.airBnbApp.repositories.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelServiceImple implements HotelService{

    private final HotelRepository hotelRepository;

    private final ModelMapper mapper;

        private final InventoryService inventoryService;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating new hotel with name : {}",hotelDto.getName());
        Hotel hotelEntity = mapper.map(hotelDto,Hotel.class);
        hotelEntity.setActive(false);

        //ToDo : activate hotel when we add its invantory
        Hotel savedHotelEntity = hotelRepository.save(hotelEntity);
        log.info("Created new hotel with id : {}",hotelDto.getId());
        return mapper.map(savedHotelEntity,HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(long id) {
        log.info("Getting hotel with id : {}",id);
        Hotel hotelEntity = hotelRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Hotel with id :" + id +" not found."));
        log.info("Got hotel with id : {}",hotelEntity.getId());
        return mapper.map(hotelEntity,HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(long id, HotelDto hotelDto) {
        log.info("Updating a hotel with id : {}",id);

        Hotel hotelEntity = hotelRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Hotel with id :" + id +" not found so update is not possible."));
        mapper.map(hotelDto,hotelEntity);
        hotelEntity.setId(id);
        Hotel updatedHotel = hotelRepository.save(hotelEntity);
        log.info("Updated hotel with id : {}",updatedHotel.getId());
        return mapper.map(updatedHotel,HotelDto.class);
    }

    @Override
    public void deleteHotelById(long id) {
        log.info("Deleting a hotel with id : {}",id);

        Hotel hotelEntity = hotelRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Hotel with id :" + id +" not found so delete is not possible."));
        hotelRepository.deleteById(id);
        log.info("Deleted hotel with id : {}",hotelEntity.getId());
    }

    @Override
    public void activateHotelyId(long id) {
        log.info("Activating a hotel with id : {}",id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel of the id :"+id+"is not found so cannot activate it"));
        hotel.setActive(true);
        //TODO : create inventory of hotel
        for(Room room : hotel.getRooms()){
            inventoryService.initilizeRoomForAYear(room);
        }
        log.info("Activated a hotel with id : {}",id);
        hotelRepository.save(hotel);

    }
}
