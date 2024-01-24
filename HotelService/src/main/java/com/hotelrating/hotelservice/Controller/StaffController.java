package com.hotelrating.hotelservice.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @GetMapping("/getAll")
    public ResponseEntity<List<String>> getStaffMembers(){
        List<String> list = Arrays.asList("Ramu", "Shyamu", "Harmeet", "Babul");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
