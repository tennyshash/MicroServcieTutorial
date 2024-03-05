package com.hotelrating.userservice.Controller;

import com.hotelrating.userservice.Entity.User;
import com.hotelrating.userservice.Services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody  User user){
        User newUser=  userService.save(user);
        return  new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

  //  int retryCount=1;
    @GetMapping("/get/{userId}")
    @CircuitBreaker(name = "UserRatingHotelCircuitBreaker")
    @Retry(name = "UserRatingHotelRetry", fallbackMethod = "userRatingHotelFallBack")
    @RateLimiter(name = "UserRatingHotelRateLimiter", fallbackMethod = "userRatingHotelFallBack")
    public ResponseEntity<User> getUserByID(@PathVariable(value = "userId") String userID){
//        just displaying count for testing  and visualising
//        logger.info("retry Count : {}" , retryCount);
//        retryCount++;

        User user= userService.getUserByID(userID);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    // Call Back Method for CIRCUIT-BREAKER

    public ResponseEntity<User>  userRatingHotelFallBack(String userID, Exception exception){
        //logger.info("Fall back is executed because service is down :", exception.getMessage());

//        exception.printStackTrace();

        User user= User.builder()
                .About("Fall Back Method is executed.")
                .Name("Dummy USER")
                .Email("Dummy@gmail.com")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users=userService.getAllUser();
        return ResponseEntity.ok(users);

    }

}
