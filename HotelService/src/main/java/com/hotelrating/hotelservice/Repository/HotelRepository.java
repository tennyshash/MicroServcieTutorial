package com.hotelrating.hotelservice.Repository;

import com.hotelrating.hotelservice.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {

    List<Hotel> findAll();

    @Override
    Optional<Hotel> findById(String hotelId);

    Hotel save(Hotel hotel);
}
