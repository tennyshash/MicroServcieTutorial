package com.hotelrating.userservice.FeignClientServices;

import com.hotelrating.userservice.Entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingServiceFeign {

    @GetMapping("/ratings/getAllByUserID/{userID}")
    List<Rating> getRatingByUser(@PathVariable String userID);
}
