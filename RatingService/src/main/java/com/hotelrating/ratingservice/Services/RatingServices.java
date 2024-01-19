package com.hotelrating.ratingservice.Services;

import com.hotelrating.ratingservice.Entity.Rating;

import java.util.List;

public interface RatingServices {
    Rating createRating(Rating rating);
    List<Rating> getAll();

    List<Rating> getAllByHotelID(String hotelID );

    List<Rating> getALlByUserID(String userID);
}
