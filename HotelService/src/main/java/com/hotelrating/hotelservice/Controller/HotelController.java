package com.hotelrating.hotelservice.Controller;

import com.hotelrating.hotelservice.Entity.Hotel;
import com.hotelrating.hotelservice.Services.HotelService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/register")
    public ResponseEntity<Hotel> register(@RequestBody Hotel hotel){
        Hotel registeredHotel=hotelService.register(hotel);
        return new ResponseEntity<>(registeredHotel, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Hotel>> getAll(){
        List<Hotel> hotels=hotelService.getAll();
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/get/{hotelID}")
    public ResponseEntity<Hotel> getByID(@PathVariable String hotelID){
        Hotel hotel= hotelService.getById(hotelID);
        return ResponseEntity.ok(hotel);
    }
}
