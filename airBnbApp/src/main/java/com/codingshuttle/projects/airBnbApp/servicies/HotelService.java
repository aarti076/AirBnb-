package com.codingshuttle.projects.airBnbApp.servicies;

import com.codingshuttle.projects.airBnbApp.dtos.HotelDto;

public interface HotelService {
    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto getHotelById(long id);
    HotelDto updateHotelById(long id,HotelDto hotelDto);
    void deleteHotelById(long id);
    void activateHotelyId(long id);
}
