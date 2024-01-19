package com.hotelrating.ratingservice.Services.Implementation;

import com.hotelrating.ratingservice.Entity.Rating;
import com.hotelrating.ratingservice.Repository.RatingRepository;
import com.hotelrating.ratingservice.Services.RatingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServcieImple implements RatingServices {

    private RatingRepository ratingRepository;

    @Autowired
    public RatingServcieImple(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllByHotelID(String hotelID) {
        return ratingRepository.findAllByHotelID(hotelID);
    }

    @Override
    public List<Rating> getALlByUserID(String userID) {
        return ratingRepository.findAllByUserID(userID);
    }
}
