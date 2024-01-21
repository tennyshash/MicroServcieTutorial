package com.hotelrating.userservice.FeignClientServices;

import com.hotelrating.userservice.Entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingServiceFeign {

    @GetMapping("/ratings/getAllByUserID/{userID}")
    ResponseEntity<List<Rating>> getRatingByUser(@PathVariable String userID);

    @PostMapping("/ratings/create")
    ResponseEntity<Rating> createRating(@RequestBody Rating rating);


}
