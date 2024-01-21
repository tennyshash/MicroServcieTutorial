package com.hotelrating.userservice.FeignClientServices;


import com.hotelrating.userservice.Entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelServiceFeign {

    @GetMapping("/hotels/get/{hotelID}")
    Hotel getHotel(@PathVariable String hotelID);
}
