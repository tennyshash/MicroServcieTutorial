package com.hotelrating.userservice.Services.Implementation;

import com.hotelrating.userservice.Entity.Hotel;
import com.hotelrating.userservice.Entity.Rating;
import com.hotelrating.userservice.Entity.User;
import com.hotelrating.userservice.Exceptions.ResourceNotFoundException;
import com.hotelrating.userservice.Repository.UserRepository;
import com.hotelrating.userservice.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RestTemplate restTemplate;
    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate=restTemplate;
    }

    @Override
    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users=userRepository.findAll();

        List<User>userList=users.stream().map( user -> {

            // fetching rating of user from rating service
            //http://localhost:7002/ratings/getAllByUserID/ + userID

            Rating [] userRating=restTemplate.getForObject("http://localhost:7002/ratings/getAllByUserID/"+user.getID(),Rating[].class);
            logger.info("{}" , userRating);
            List<Rating> ratings= Arrays.stream(userRating).toList();

            List<Rating> ratingsListWithHotel=ratings.stream().map( rating -> {
                // Api to call hotel services to get hotel
                //http://localhost:7001/hotels/get/

                ResponseEntity<Hotel> hotelEntity=restTemplate.getForEntity("http://localhost:7001/hotels/get/"+rating.getHotelID(), Hotel.class);
                Hotel newHotel=hotelEntity.getBody();
                logger.info(" status code : {}", hotelEntity.getStatusCode() );

                rating.setHotel(newHotel);
                return  rating;

            }).collect(Collectors.toList());

            user.setRating(ratingsListWithHotel);
            return user;

        }).collect(Collectors.toList());

        return userList;
    }

    @Override
    public User getUserByID(String userID)  {

        // Fetching user from User service.
        User user= userRepository.findById(userID).orElseThrow(( ()-> new ResourceNotFoundException("Resource Not  Found with ID" + userID)));

        // fetching rating of user from rating service
        //http://localhost:7002/ratings/getAllByUserID/ + userID

        Rating [] usersRating=restTemplate.getForObject("http://localhost:7002/ratings/getAllByUserID/"+user.getID(), Rating[].class);
        logger.info("{}",usersRating);
        List<Rating> usersRatingList= Arrays.stream(usersRating).toList();

        List<Rating> ratings=usersRatingList.stream().map( rating-> {
            // Api to call hotel services to get hotel
            //http://localhost:7001/hotels/get/
            ResponseEntity<Hotel> hotelEntity =restTemplate.getForEntity("http://localhost:7001/hotels/get/"+rating.getHotelID(), Hotel.class);
            Hotel hotel=hotelEntity.getBody();
            logger.info("status code : {}",hotelEntity.getStatusCode());

            rating.setHotel( hotel);
            return rating;

        }).collect(Collectors.toList());

        user.setRating(ratings);

        return user;
    }
}
