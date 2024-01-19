package com.hotelrating.hotelservice.Services;

import com.hotelrating.hotelservice.Entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel register(Hotel hotel);

    List<Hotel> getAll();

    Hotel getById(String hotlelID);
}
