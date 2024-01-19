package com.hotelrating.ratingservice.Repository;

import com.hotelrating.ratingservice.Entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {

    Rating save(Rating rating);

    List<Rating> findAll();


    List<Rating> findAllByHotelID(String hotelID);
    List<Rating> findAllByUserID(String userID);
}
