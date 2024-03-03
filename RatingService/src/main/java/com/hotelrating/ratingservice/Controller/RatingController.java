package com.hotelrating.ratingservice.Controller;

import com.hotelrating.ratingservice.Entity.Rating;
import com.hotelrating.ratingservice.Services.RatingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private RatingServices ratingServices;

    @Autowired
    public RatingController(RatingServices ratingServices) {
        this.ratingServices = ratingServices;
    }

    @PreAuthorize(" hasAuthority('Admin') " )
    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody  Rating rating){

        return ResponseEntity.status(HttpStatus.CREATED).body(ratingServices.createRating(rating));
//        Rating newRating= ratingServices.createRating(rating);
//        return new ResponseEntity<>(newRating, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Rating>> getAllRating(){
        List<Rating> ratings =ratingServices.getAll();
        return ResponseEntity.ok(ratings);
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin') " )
    @GetMapping("/getAllByUserID/{userID}")
    public ResponseEntity<List<Rating>> getAllByUserID(@PathVariable String userID){
        List<Rating> ratings =ratingServices.getALlByUserID(userID);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/getAllByHotelID/{hotelID}")
    public ResponseEntity<List<Rating>> getAllByHotel(@PathVariable String hotelID){
        List<Rating> ratings =ratingServices.getAllByHotelID(hotelID);
        return ResponseEntity.ok(ratings);
    }
}
