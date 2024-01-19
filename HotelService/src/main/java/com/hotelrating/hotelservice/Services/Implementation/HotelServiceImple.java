package com.hotelrating.hotelservice.Services.Implementation;

import com.hotelrating.hotelservice.Entity.Hotel;
import com.hotelrating.hotelservice.Exceptions.ResourceNotFoundException;
import com.hotelrating.hotelservice.Repository.HotelRepository;
import com.hotelrating.hotelservice.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImple implements HotelService {

    private HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImple(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel register(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getById(String hotlelID) {
        return hotelRepository.findById(hotlelID).orElseThrow( ()-> new ResourceNotFoundException("Hotel Not found with :"+hotlelID));
    }
}
