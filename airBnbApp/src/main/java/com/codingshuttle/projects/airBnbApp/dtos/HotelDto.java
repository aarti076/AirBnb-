package com.codingshuttle.projects.airBnbApp.dtos;

import com.codingshuttle.projects.airBnbApp.entities.HotelContactInfo;
import com.codingshuttle.projects.airBnbApp.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Data
public class HotelDto {

    private long id;

    private String name;

    private String city;

    private String[] photos;

    private String[] amenities;

    private Boolean active;

    private HotelContactInfo contactInfo;

    private User owner;
}
