package com.hotelrating.userservice.Controller;

import com.hotelrating.userservice.Entity.User;
import com.hotelrating.userservice.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody  User user){
        User newUser=  userService.save(user);
        return  new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @GetMapping("/get/{userId}")
    public ResponseEntity<User> getUserByID(@PathVariable(value = "userId") String userID){
        User user= userService.getUserByID(userID);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users=userService.getAllUser();
        return ResponseEntity.ok(users);
    }
}
